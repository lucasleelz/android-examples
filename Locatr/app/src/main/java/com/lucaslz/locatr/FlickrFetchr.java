package com.lucaslz.locatr;

import android.location.Location;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/20.
 */
public class FlickrFetchr {

    private static final String TAG = "FlickrFetchr";
    private static final String API_KEY = "0d328625487f4b99a1547d1ca938ed98";
    private static final String ser = "650be9a0c74f5ac7";
    private static final String FETCH_RECENTS_METHOD = "flickr.photos.getRecent";
    private static final String SEARCH_METHOD = "flickr.photos.search";

    private static final Uri ENDPOINT = Uri.parse("https://api.flickr.com/services/rest/")
            .buildUpon()
            .appendQueryParameter("api_key", API_KEY)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("extras", "url_s, geo") // 有小图片就直接返回URL。
            .build();

    public List<GalleryItem> searchPhotos(String query) {
        return downloadGalleryItem(
                buildUrl(SEARCH_METHOD, query)
        );
    }

    public List<GalleryItem> searchPhotos(Location location) {
        return downloadGalleryItem(buildUrl(location));
    }

    public List<GalleryItem> fetchRecentPhotos() {
        return downloadGalleryItem(
                buildUrl(FETCH_RECENTS_METHOD, null)
        );
    }

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": With " + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    private String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    private List<GalleryItem> parseItems(JSONObject jsonBody) throws JSONException {
        JSONObject photosJSONObject = jsonBody.getJSONObject("photos");
        JSONArray photoJSONArray = photosJSONObject.getJSONArray("photo");

        List<GalleryItem> items = new ArrayList<>();
        for (int index = 0; index < photoJSONArray.length(); index++) {
            JSONObject photoJsonObject = photoJSONArray.getJSONObject(index);

            GalleryItem item = new GalleryItem();
            item.setId(photoJsonObject.getString("id"));
            item.setCaption(photoJsonObject.getString("title"));

            if (!photoJsonObject.has("url_s")) {
                continue;
            }
            item.setUrl(photoJsonObject.getString("url_s"));
            item.setOwner(photoJsonObject.getString("owner"));
            item.setLat(photoJsonObject.getDouble("latitude"));
            item.setLon(photoJsonObject.getDouble("longitude"));
            items.add(item);
        }
        return items;
    }

    private List<GalleryItem> downloadGalleryItem(String url) {
        List<GalleryItem> items = new ArrayList<>();
        try {
            String jsonString = getUrlString(url);
            JSONObject jsonBody = new JSONObject(jsonString);
            return parseItems(jsonBody);
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch items", e);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON", e);
        }
        return Collections.emptyList();
    }


    private String buildUrl(String method, String query) {
        Uri.Builder builder = ENDPOINT.buildUpon().appendQueryParameter("method", method);
        if (method.equals(SEARCH_METHOD)) {
            builder.appendQueryParameter("text", query);
        }
        return builder.build().toString();
    }

    private String buildUrl(Location location) {
        return ENDPOINT.buildUpon()
                .appendQueryParameter("method", SEARCH_METHOD)
                .appendQueryParameter("lat", "" + location.getLatitude())
                .appendQueryParameter("lon", "" + location.getLongitude())
                .build().toString();
    }

}
