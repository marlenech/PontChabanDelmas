package com.italikdesign.pont.chaban;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by italikdesign on 29/05/2016.
 *
 * For getRessources to call in a static context
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        OneSignal.startInit(this).setNotificationOpenedHandler(new ExampleNotificationOpenedHandler()).init();
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {


            }
        });

    }
    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult openedResult) {
            OSNotification notification = openedResult.notification;
            JSONObject data = notification.payload.additionalData;
            OSNotificationAction.ActionType actionType = openedResult.action.type;

            String customKey = data.optString("customkey", null);
            if (actionType == OSNotificationAction.ActionType.ActionTaken)
                Log.i("OneSignalExample", "Button pressed with id: " + openedResult.action.actionID);

            if (data != null)
                Log.d("OneSignalExample", "Full additionalData:\n" + data.toString());

            Log.d("OneSignalExample", "App in focus: " + notification.isAppInFocus);
        }
    }


    public static Context getContext(){
        return mContext;
    }


}