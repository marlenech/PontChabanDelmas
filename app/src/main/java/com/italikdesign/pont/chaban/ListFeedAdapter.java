package com.italikdesign.pont.chaban;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TooManyListenersException;

import static java.time.LocalTime.parse;
import static org.apache.http.client.utils.DateUtils.parseDate;


/**
 * Created by italikdesign on 29/05/2016.
 */
public class ListFeedAdapter extends RecyclerView.Adapter<ListFeedAdapter.MyViewHolder> {




    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Feed> data;
    public View view;
    String inputFormat = "HH:mm";
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);



    public ListFeedAdapter(Context context, ArrayList<Feed> objects) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = objects;
    }

    public Date parseDate1 (String date) {


        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }

    }



    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       view = inflater.inflate(R.layout.feed_view, parent, false);
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

        GradientDrawable gd = new GradientDrawable();

        FrameLayout contourHaut = view.findViewById(R.id.contourhaut);
        FrameLayout contourBas = view.findViewById(R.id.contourbas);
        RelativeLayout cardView = view.findViewById(R.id.relativeLayout);

        LinearLayout.LayoutParams prms = new LinearLayout.LayoutParams(5, 2);

        //////Adaptation couleur Sens en fonction arrivée et départ/////////////

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


        //////Modification du subtitle de l'appli en fonction de la date et l'heure actuelle///////
        //////avec comparaison date, année et heure des feeds//////////////////////////////////////

        //date actuelle
        Date now = new Date();
        SimpleDateFormat formatterDate = new SimpleDateFormat("EEEE dd MMMM");
        SimpleDateFormat formatterHeure = new SimpleDateFormat("HH:mm");
        String resultDate = formatterDate.format(now);
        String resultHeure = formatterHeure.format(now);





        //Récupération des données feeds
        String jourText = feeds.getJour();
        String dateText = feeds.getDate();
        String heureDebText = feeds.getHeure1();
        String heureFinText = feeds.getHeure2();

        //variables
        Date date;
        Date dateCompareOne;
        Date dateCompareTwo;



        //conversion des String en Date (feeds)
        dateCompareOne = parseDate1(heureDebText);
        dateCompareTwo = parseDate1(heureFinText);

        //conversion de l'heure actuelle au format Date
        date = parseDate1(resultHeure);

        //Création d'un string pour pouvoir modifier subtitle dans l'adapter + attribution couleur + typeface
        SpannableString spannableString = new SpannableString(context.getString(R.string.ferme));
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.rouge_depart)), 0, spannableString.toString()
                .length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Typeface typo = ResourcesCompat.getFont(context, R.font.roboto_regular);


        //Toast.makeText(context, resultHeure, Toast.LENGTH_LONG).show();

        //vérifier si la date actuelle est la même que celle des feeds
        if((jourText.concat(" ").concat(dateText)).equals(resultDate)) {
            if (dateCompareOne.before(date) && dateCompareTwo.after(date)) {
                holder.jour.setTextColor(context.getResources().getColor(R.color.rouge_depart));
                holder.jour.setTypeface(typo);
                holder.date.setTextColor(context.getResources().getColor(R.color.rouge_depart));
                holder.date.setTypeface(typo);
                holder.annee.setTextColor(context.getResources().getColor(R.color.rouge_depart));
                holder.annee.setTypeface(typo);
                holder.heure1.setTextColor(context.getResources().getColor(R.color.rouge_depart));
                holder.heure1.setTypeface(typo);
                holder.heure2.setTextColor(context.getResources().getColor(R.color.rouge_depart));
                holder.heure2.setTypeface(typo);
                ((MainActivity) context).getSupportActionBar().setSubtitle(spannableString);
                contourHaut.setBackgroundColor(context.getResources().getColor(R.color.rouge_depart));
                contourBas.setBackgroundColor(context.getResources().getColor(R.color.rouge_depart));
                cardView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
        }


    }



    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

   public static class MyViewHolder extends RecyclerView.ViewHolder {
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