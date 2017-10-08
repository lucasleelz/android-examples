package lucaslz.com.component.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lucas on 2017/10/7.
 */
public final class ActivityCollector {

    private static final List<Activity> sActivities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivities.remove(activity);
    }

    public static void finishAllActivities() {
        for (Activity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        sActivities.clear();
    }

    private ActivityCollector() {
    }
}
