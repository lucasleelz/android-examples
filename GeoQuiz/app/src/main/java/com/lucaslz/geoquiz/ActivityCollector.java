package com.lucaslz.geoquiz;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动收集器。
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/5.
 */
public class ActivityCollector {

    private static List<Activity> sActivities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        sActivities.clear();
    }
}
