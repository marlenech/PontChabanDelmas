package com.italikdesign.pont.chaban;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

/**
 * Created by italikdesign on 29/05/2016.
 */
public class WidgetProvider extends AppWidgetProvider {

    // String to be sent on Broadcast as soon as Data is Fetched
    // should be included on WidgetProvider manifest intent action
    // to be recognized by this WidgetProvider to receive broadcast
    public static final String DATA_FETCHED = "com.italikdesign.pont.chaban.DATA_FETCHED";

    /*
     * this method is called every 30 mins as specified on widgetinfo.xml this
     * method is also called on every phone reboot from this method nothing is
     * updated right now but instead RetmoteFetchService class is called this
     * service will fetch data,and send broadcast to WidgetProvider this
     * broadcast will be received by WidgetProvider onReceive which in turn
     * updates the widget
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            Intent serviceIntent = new Intent(context, RemoteFetchService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    appWidgetIds[i]);

                context.startService(serviceIntent);

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private RemoteViews updateWidgetListView(Context context, int appWidgetId) {

        // which layout to show on widget
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);

        // When we click the widget, we want to open our main activity.
        Intent launchActivity = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0, launchActivity, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget, pendingIntent);

        ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thisWidget, remoteViews);

        // RemoteViews Service needed to provide adapter for ListView
        Intent svcIntent = new Intent(context, WidgetService.class);
        // passing app widget id to that RemoteViews Service
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        // setting a unique Uri to the intent
        // don't know its purpose to me right now
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        // setting adapter to listview of the widget
        remoteViews.setRemoteAdapter(appWidgetId, R.id.listViewWidget,
                svcIntent);
        // setting an empty view in case of no data
        remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);
        return remoteViews;
    }

    /*
     * It receives the broadcast as per the action set on intent filters on
     * Manifest.xml once data is fetched from RemotePostService,it sends
     * broadcast and WidgetProvider notifies to change the data the data change
     * right now happens on ListProvider as it takes RemoteFetchService
     * listItemList as data
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(DATA_FETCHED)) {
            int appWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            AppWidgetManager appWidgetManager = AppWidgetManager
                    .getInstance(context);


            final ComponentName cn = new ComponentName(context,
                    WidgetProvider.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(
                    appWidgetManager.getAppWidgetIds(cn),R.id.listViewWidget);
            RemoteViews remoteViews = updateWidgetListView(context, appWidgetId);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);



        }
    }

}

