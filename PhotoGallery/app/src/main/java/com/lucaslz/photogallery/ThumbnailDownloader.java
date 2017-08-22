package com.lucaslz.photogallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.util.LruCache;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/23.
 */
public class ThumbnailDownloader<T> extends HandlerThread {
    private static final String TAG = "ThumbnailDownloader";
    private static final int MESSAGE_DOWNLOAD = 0;

    private Boolean mHasQuit = false;
    private Handler mRequestHandler;
    private ConcurrentMap<T, String> mRequestMap = new ConcurrentHashMap<>();
    private Handler mResponseHandler;
    private ThumbnailDownloaderListener<T> mThumbnailDownloaderListener;
    private LruCache<String, Bitmap> mThumbnailCache = new LruCache<>(100);

    public interface ThumbnailDownloaderListener<T> {
        void onThumbnailDownloaded(T target, Bitmap thumbnail);
    }

    public void setThumbnailDownloaderListener(ThumbnailDownloaderListener<T> thumbnailDownloaderListener) {
        mThumbnailDownloaderListener = thumbnailDownloaderListener;
    }

    public ThumbnailDownloader(Handler responseHandler) {
        super(TAG, Process.THREAD_PRIORITY_LESS_FAVORABLE);
        mResponseHandler = responseHandler;
    }

    @Override
    public boolean quit() {
        mHasQuit = true;
        return super.quit();
    }

    public void queueThumbnail(T target, String url) {
        Log.i(TAG, "Got a URL:" + url);

        if (url == null) {
            mRequestMap.remove(target);
            return;
        }

        if (mThumbnailCache.get(url) != null) {
            mRequestMap.remove(target);
            mThumbnailDownloaderListener.onThumbnailDownloaded(target, mThumbnailCache.get(url));
            return;
        }

        mRequestMap.put(target, url);
        mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD, target).sendToTarget();

    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_DOWNLOAD) {
                    T target = (T) msg.obj;
                    Log.i(TAG, "Got a message for URL:" + mRequestMap.get(target));
                    handleRequest(target);
                }
            }
        };
    }

    public void clearQueue() {
        mRequestHandler.removeMessages(MESSAGE_DOWNLOAD);
        mRequestMap.clear();
    }

    private void handleRequest(final T target) {
        final String url = mRequestMap.get(target);
        if (url == null) {
            return;
        }
        try {
            byte[] bitmapBytes = new FlickrFetchr().getUrlBytes(url);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            mResponseHandler.post(() -> {
                if (!mRequestMap.get(target).equals(url) || mHasQuit) {
                    return;
                }
                mThumbnailCache.put(url, bitmap);
                mRequestMap.remove(target);
                mThumbnailDownloaderListener.onThumbnailDownloaded(target, bitmap);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
