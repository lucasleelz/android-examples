package com.lucaslz.photogallery;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/9/13.
 */
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() != Activity.RESULT_OK) {
            return;
        }
        int requestCode = intent.getIntExtra(PollService.REQUEST_CODE_KEY, 0);
        Notification notification = intent.getParcelableExtra(PollService.NOTIFICATION_KEY);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(requestCode, notification);
    }
}
