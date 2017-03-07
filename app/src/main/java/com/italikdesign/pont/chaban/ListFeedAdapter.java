package com.italikdesign.pont.chaban;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by italikdesign on 29/05/2016.
 */
public class ListFeedAdapter extends RecyclerView.Adapter<ListFeedAdapter.MyViewHolder> {




    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Feed> data;

    public ListFeedAdapter(Context context, ArrayList<Feed> objects) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = objects;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.feed_view, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Feed feeds = data.get(position);

        holder.title.setText(feeds.getTitle());
        holder.heure.setText(feeds.getHeure());
        holder.description.setText(feeds.getDescription());
        holder.passage.setText(feeds.getPassage());
    }

    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView heure;
        TextView description;
        TextView passage;


        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            heure = (TextView) itemView.findViewById(R.id.heure);
            description = (TextView) itemView.findViewById(R.id.description);
            passage = (TextView) itemView.findViewById(R.id.passage);


        }
    }

}