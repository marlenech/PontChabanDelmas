package com.italikdesign.pont.chaban;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViewsService;

/**
 * Created by italikdesign on 29/05/2016.
 */
public class WidgetService extends RemoteViewsService {
	/*
	 * So pretty simple just defining the Adapter of the listview
	 * here Adapter is ListProvider
	 * */

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        return (new ListProvider(this, intent));
    }



}
