package com.italikdesign.pont.chaban;

import android.content.Context;
import android.graphics.Color;
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

        holder.jour.setText(feeds.getJour());
        holder.date.setText(feeds.getDate());
        holder.annee.setText(feeds.getAnnee());
        holder.sens.setText(feeds.getSens());
        holder.bateaux.setText(feeds.getBateaux());
        holder.heure1.setText(feeds.getHeure1());
        holder.heure2.setText(feeds.getHeure2());
        holder.motif.setText(feeds.getMotif());
        holder.heurepassage.setText(feeds.getHeurepassage());

    }

    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jour;
        TextView date;
        TextView annee;
        public TextView sens;
        TextView bateaux;
        TextView heure1;
        TextView heure2;
        TextView motif;
        TextView heurepassage;


        public MyViewHolder(View itemView) {
            super(itemView);
            jour = itemView.findViewById(R.id.jour);
            date = itemView.findViewById(R.id.date);
            annee = itemView.findViewById(R.id.annee);
            sens = itemView.findViewById(R.id.sens);
            bateaux = itemView.findViewById(R.id.bateaux);
            heure1 = itemView.findViewById(R.id.heure1);
            heure2 = itemView.findViewById(R.id.heure2);
            motif = itemView.findViewById(R.id.motif);
            heurepassage = itemView.findViewById(R.id.heurepassage);



        }
    }

}