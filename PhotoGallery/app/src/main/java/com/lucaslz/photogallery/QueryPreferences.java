package com.lucaslz.photogallery;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/9/8.
 */
public class QueryPreferences {

    private static final String PREF_SEARCH_QUERY = "search_query";

    public static String getStoredQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SEARCH_QUERY, null);
    }

    public static void setStoredQuery(Context context, String query) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_QUERY, query)
                .apply();
    }
}
