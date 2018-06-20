package com.italikdesign.pont.chaban;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;


/**
 * Created by italikdesign on 29/05/2016.
 */
public class ListProvider implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
    private Context context;
    private int appWidgetId;

    public ListProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        populateListItem();
    }

    private void populateListItem() {
        if(RemoteFetchService.listItemList !=null )
            listItemList = (ArrayList<ListItem>) RemoteFetchService.listItemList
                    .clone();
        else
            listItemList = new ArrayList<ListItem>();



    }
    public void onDataSetChanged() {
        if(RemoteFetchService.listItemList !=null)

            listItemList = (ArrayList<ListItem>) RemoteFetchService.listItemList
                    .clone();
        else
            listItemList = new ArrayList<ListItem>();
    }




    @Override
    public int getCount() {
        return listItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {

        if (Build.VERSION.SDK_INT > 20) {
            final RemoteViews remoteView = new RemoteViews(
                    context.getPackageName(), R.layout.list_row);
            ListItem listItem = listItemList.get(position);
            remoteView.setTextViewText(R.id.title, listItem.title);
            remoteView.setTextViewText(R.id.description, listItem.description);
            remoteView.setTextViewText(R.id.heure, listItem.heure);
            remoteView.setImageViewResource(R.id.listViewWidget, R.id.date);
            return remoteView;

        }
        else{
            final RemoteViews remoteView = new RemoteViews(
                    context.getPackageName(), R.layout.list_row_noicon);
            ListItem listItem = listItemList.get(position);
            remoteView.setTextViewText(R.id.title, listItem.title);
            remoteView.setTextViewText(R.id.description, listItem.description);
            remoteView.setTextViewText(R.id.heure, listItem.heure);

            return remoteView;
        }




    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }



    @Override
    public void onDestroy() {
    }

}