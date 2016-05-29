package com.italikdesign.pont.chaban;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by italikdesign on 29/05/2016.
 */
public class ListFeedAdapter extends BaseAdapter {

    // the data to display
    ImageView thumbnail;
    private ArrayList<Feed> feeds;

    // LayoutInflater for parse layout XML in IHM Android.

    private LayoutInflater inflater;


    public ListFeedAdapter(Context context, ArrayList<Feed> feeds) {
        inflater = LayoutInflater.from(context);
        this.feeds = feeds;

    }

    // * Must specified Method "count()".
    // * Number of items in list

    @Override
    public int getCount() {
        return feeds.size();

        //TODO
    }


    // Return object of list

    @Override
    public Object getItem(int index) {
        return feeds.get(index);
    }

    @Override
    public long getItemId(int index) {
        return this.feeds.get(index).getId();

    }


    // * Part complex

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // * "convertView" for recycler items

        FeedView fv;

        if (convertView == null) {
            fv = new FeedView();
            convertView = inflater.inflate(R.layout.feed_view, null);

            fv.imageView = (ImageView)convertView.findViewById(R.id.thumbnail);
            fv.title = (TextView)convertView.findViewById(R.id.title);
            fv.description = (TextView)convertView.findViewById(R.id.description);
            fv.heure = (TextView)convertView.findViewById(R.id.heure);

            convertView.setTag(fv);

        } else {
            fv = (FeedView) convertView.getTag();
        }
        fv.imageView.setImageBitmap(feeds.get(position).getImageBitmap());
        fv.description.setText(feeds.get(position).getDescription());
        fv.title.setText(feeds.get(position).getTitle());
        fv.heure.setText(feeds.get(position).getTitle());

        return convertView;

    }

}
