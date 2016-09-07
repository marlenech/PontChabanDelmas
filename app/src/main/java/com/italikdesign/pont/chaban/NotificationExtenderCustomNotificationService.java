package com.italikdesign.pont.chaban;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationPayload;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by italikdesign on 05/09/16.
 */
public class NotificationExtenderCustomNotificationService extends NotificationExtenderService {

    public NotificationExtenderCustomNotificationService() {

    }

    @SuppressLint("LongLogTag")
    @Override
    protected boolean onNotificationProcessing(OSNotificationPayload notification) {
        Log.e("onNotificationProcessing","called");
        boolean isAppInBackground = isAppIsInBackground(getApplicationContext());

        if(notification.backgroundData){
            JSONObject json = notification.additionalData;
            String jsonStr = null;
            try {
                if(json != null){
                    jsonStr = json.getString("Prochaine levée");
                    Log.e("additional data :- ", jsonStr );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String title = notification.title;
            String description = notification.message;

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String action = jsonObject.optString("Action");
            String contentType = jsonObject.optString("Content_Type");
            String contentId = jsonObject.optString("Content_Id");

            OverrideSettings overrideSettings = new OverrideSettings();
            overrideSettings.extender = new NotificationCompat.Extender() {
                // Apply this extender to a notification builder.

                //@param builder the builder to be modified.
                //@return the build object for chaining.

                @Override
                public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                    builder.setContentTitle("Prochaine levée");
                    builder.setContentText("text");
                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText("text"));
                    builder.setSmallIcon(R.mipmap.ic_launcher);


                    return builder;

                }
            };
            OSNotificationDisplayedResult result = displayNotification(overrideSettings);
            return true;
        }else{
            return false;
        }

 /*       String title = notification.title;
        String message = notification.message;
        JSONObject jsonObject = notification.additionalData;
        if(jsonObject != null){
            String jsonString = jsonObject.toString();
            String jsonStr = jsonString;
        }
        sendNotification(title,message);*/
    }

    /**
     * Method checks if the app is in background or not
     *
     * @param context
     * @return
     */
    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    private void sendNotification(String title,String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        if(title != null && !title.equalsIgnoreCase("") && !title.isEmpty()){
            notificationBuilder.setContentTitle("Prochaine levée");
        }else{
            notificationBuilder.setContentTitle("Title (use console to change)");
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)System.currentTimeMillis(), notificationBuilder.build());
    }


}
