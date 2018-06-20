package com.italikdesign.pont.chaban;


import android.app.Notification;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;


/**
 * Created by italikdesign on 29/05/2016.
 */
public class RemoteFetchService extends Service {

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private AQuery aquery;
    private String remoteJsonUrl = App.getContext().getString(R.string.address_json);

    public static ArrayList<ListItem> listItemList;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    /*
     * Retrieve appwidget id from intent it is needed to update widget later
     * initialize our AQuery class
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra(AppWidgetManager.EXTRA_APPWIDGET_ID))
            appWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        aquery = new AQuery(getBaseContext());
        fetchDataFromWeb();
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int NOTIFICATION_ID = (int) (System.currentTimeMillis()%10000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID, new Notification.Builder(this).build());
        }


    }
    /**
     * method which fetches data(json) from web aquery takes params
     * remoteJsonUrl = from where data to be fetched String.class = return
     * format of data once fetched i.e. in which format the fetched data be
     * returned AjaxCallback = class to notify with data once it is fetched
     */
    private void fetchDataFromWeb() {
        aquery.ajax(remoteJsonUrl, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String result, AjaxStatus status) {
                processResult(result);
                super.callback(url, result, status);
            }
        });
    }

    /**
     * Json parsing of result and populating ArrayList<ListItem> as per json
     * data retrieved from the string
     */
    private void processResult(String result) {
        Log.i("Resutl",result);
        listItemList = new ArrayList<ListItem>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ListItem listItem = new ListItem();
                listItem.title = jsonObject.getString("title");
                listItem.description = jsonObject.getString("description");
                listItem.heure = jsonObject.getString("heure");

                Log.i("Heading",listItem.title);
                Log.i("Content",listItem.description);
                Log.i("Heure",listItem.heure);

                listItemList.add(listItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        populateWidget();
    }

    /**
     * Method which sends broadcast to WidgetProvider
     * so that widget is notified to do necessary action
     * and here action == WidgetProvider.DATA_FETCHED
     */
    private void populateWidget() {

        Intent widgetUpdateIntent = new Intent();

        widgetUpdateIntent.setAction(WidgetProvider.DATA_FETCHED);
        widgetUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                appWidgetId);
        sendBroadcast(widgetUpdateIntent);

        this.stopSelf();
    }

}