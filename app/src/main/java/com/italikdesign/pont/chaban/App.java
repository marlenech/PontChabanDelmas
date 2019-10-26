package com.italikdesign.pont.chaban;


import android.content.Context;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import android.util.Log;



import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;



import org.json.JSONObject;

/**
 * Created by italikdesign on 29/05/2016.
 *
 * For getRessources to call in a static context
 */
public class App extends MultiDexApplication {

    private static Context mContext;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        OneSignal.startInit(this)
                .autoPromptLocation(true)

                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())

                .init();

    }

    private class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        /**
         * Callback to implement in your app to handle when a notification is received while your app running
         *  in the foreground or background.
         *
         *  Use a NotificationExtenderService instead to receive an event even when your app is closed (not 'forced stopped')
         *     or to override notification properties.
         *
         * @param notification Contains information about the notification received.
         */
        @Override
        public void notificationReceived(OSNotification notification) {
            Log.w("OneSignalExample", "notificationReceived!!!!!!");
        }
    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        /**
         * Callback to implement in your app to handle when a notification is opened from the Android status bar or in app alert
         *
         * @param openedResult Contains information about the notification opened and the action taken on it.
         */
        @Override
        public void notificationOpened(OSNotificationOpenResult openedResult) {
            OSNotificationAction.ActionType actionType = openedResult.action.type;
            JSONObject data = openedResult.notification.payload.additionalData;
            String customKey;

            if (data != null) {
                customKey = data.optString("customkey", null);
                if (customKey != null)
                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
            }

            if (actionType == OSNotificationAction.ActionType.ActionTaken)
                Log.i("OneSignalExample", "Button pressed with id: " + openedResult.action.actionID);


            OneSignal.startInit(mContext)
                    .autoPromptLocation(true)

                    .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                    .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())

                    .init();

            // The following can be used to open an Activity of your choice.

            // Intent intent = new Intent(getApplication(), YourActivity.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            // startActivity(intent);

            // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
            //  if you are calling startActivity above.
         /*
            <application ...>
              <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
            </application>
         */
        }
    }




    public static Context getContext(){
        return mContext;
    }


}