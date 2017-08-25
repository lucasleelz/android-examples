package com.lucaslz.photogallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import java.io.IOException;
import java.lang.ref.WeakReference;
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

    protected void setThumbnailDownloaderListener(ThumbnailDownloaderListener<T> listener) {
        mThumbnailDownloaderListener = listener;
    }

    protected ThumbnailDownloader(Handler responseHandler) {
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

        if (TextUtils.isEmpty(url)) {
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
        mRequestHandler = new RequestHandler(this);
    }

    private final static class RequestHandler extends Handler {
        private WeakReference<ThumbnailDownloader> mThumbnailDownloaderWeakReference;

        private RequestHandler(ThumbnailDownloader reference) {
            mThumbnailDownloaderWeakReference = new WeakReference<>(reference);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MESSAGE_DOWNLOAD) {
                return;
            }
            ThumbnailDownloader thumbnailDownloader = mThumbnailDownloaderWeakReference.get();
            if (thumbnailDownloader == null) {
                return;
            }
            thumbnailDownloader.handleRequest(msg.obj);
        }
    }

    protected void clearQueue() {
        mRequestHandler.removeMessages(MESSAGE_DOWNLOAD);
        mRequestMap.clear();
    }

    private void handleRequest(final Object obj) {
        try {
            T target = (T) obj;
            final String url = mRequestMap.get(target);
            if (TextUtils.isEmpty(url)) {
                mRequestMap.remove(target);
                Log.i(TAG, "Got a message for URL:" + url);
                return;
            }
            byte[] bitmapBytes = new FlickrFetchr().getUrlBytes(url);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            mResponseHandler.post(() -> {
                // 使用equals 会导致闪退。
                String currentUrl = mRequestMap.get(target);
                if (TextUtils.isEmpty(currentUrl) || !url.equals(mRequestMap.get(target)) || mHasQuit) {
                    mRequestMap.remove(target);
                    Log.i(TAG, "URl 不相等:");
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
