package com.italikdesign.pont.chaban;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        this.context = context;

        holder.jour.setText(feeds.getJour());
        holder.date.setText(feeds.getDate());
        holder.annee.setText(feeds.getAnnee());
        holder.sens.setText(feeds.getSens());
        holder.bateaux.setText(feeds.getBateaux());
        holder.heure1.setText(feeds.getHeure1());
        holder.heure2.setText(feeds.getHeure2());
        holder.motif.setText(feeds.getMotif());
        holder.heurepassage.setText(feeds.getHeurepassage());

        String sensText = feeds.getSens();

        Drawable sensBackground = ContextCompat.getDrawable(context, R.drawable.encadre);

        GradientDrawable gd = new GradientDrawable();

        LinearLayout.LayoutParams prms = new LinearLayout.LayoutParams(5, 2);

        //Si le texte de sens est égal à "Arrivée", le texte et l'encadré passe en vert
        if(sensText.equalsIgnoreCase("Arrivée")) {
                holder.sens.setTextColor(context.getResources().getColor(R.color.vert_arrivee));

                gd.setStroke(2, context.getResources().getColor(R.color.vert_arrivee));
                gd.setCornerRadius(10);
                holder.sens.setPadding(15, 2, 15, 2);
                holder.sens.setBackground(gd);

        }
        //Si le texte de sens est égal à "Départ", le texte et l'encadré passe en rouge
        else if (sensText.equalsIgnoreCase("Départ")) {
            holder.sens.setTextColor(context.getResources().getColor(R.color.rouge_depart));
            gd.setStroke(2, context.getResources().getColor(R.color.rouge_depart));
            gd.setCornerRadius(10);
            holder.sens.setPadding(15, 2, 15, 2);
            holder.sens.setBackground(gd);
        }
        //Le texte et l'encadré passe en gris
        else {
            holder.sens.setTextColor(context.getResources().getColor(R.color.colorPrimary2));
            gd.setStroke(2, context.getResources().getColor(R.color.colorPrimary2));
            gd.setCornerRadius(10);
            holder.sens.setPadding(15, 2, 15, 2);
            holder.sens.setBackground(gd);
        }

    }



    public int getItemCount() {
        return data.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jour;
        TextView date;
        TextView annee;
        TextView sens;
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