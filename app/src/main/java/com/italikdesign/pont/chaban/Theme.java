package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class Theme extends Fragment {

    private View rootView;


    boolean checkSombre;
    boolean themeSombre;





    public Theme() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Le Pont Chaban Delmas");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("Thème de l'application");

        rootView = inflater.inflate(R.layout.theme, container, false);
        // Inflate the layout for this fragment



        SharedPreferences prefsTheme = getActivity().getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        checkSombre = prefsTheme.getBoolean("themeSombre", true);



        //Définition fond fenetre principale
        final LinearLayout linearLayout = rootView.findViewById(R.id.linear);
        final RelativeLayout recycler = rootView.findViewById(R.id.themerelative);
        final FrameLayout contourHaut = rootView.findViewById(R.id.contourhaut);
        final FrameLayout contourBas = rootView.findViewById(R.id.contourbas);
        final CardView cardView = rootView.findViewById(R.id.card_view_sombre);
        final ImageView palette = rootView.findViewById(R.id.palette);
        final TextView themeText = rootView.findViewById(R.id.themetext);

        palette.setColorFilter(getResources().getColor(R.color.vert_arrivee));

        final Switch themeSwitchSombre = (Switch) rootView.findViewById(R.id.themeSombre);

        final TextView theme_pref_okSombre = (TextView) rootView.findViewById(R.id.theme_pref_oksombre);

        final TextView theme_pref_noSombre = (TextView) rootView.findViewById(R.id.theme_pref_nosombre);







        //set the switch to ON Sombre
        if (checkSombre) {
            themeSwitchSombre.setChecked(true);
            theme_pref_noSombre.setVisibility(TextView.GONE);
            theme_pref_okSombre.setTextColor(getResources().getColor(R.color.windowBackground));
            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            recycler.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            contourHaut.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
            contourBas.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
            cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            themeText.setTextColor(getResources().getColor(R.color.windowBackground));
            themeSwitchSombre.setTextColor(getResources().getColor(R.color.windowBackground));
        }
        else {
            themeSwitchSombre.setChecked(false);
            theme_pref_okSombre.setVisibility(TextView.GONE);
            theme_pref_noSombre.setTextColor(getResources().getColor(R.color.marronclair));
            linearLayout.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            contourHaut.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            contourBas.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            cardView.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            themeText.setTextColor(getResources().getColor(R.color.marronclair));
            themeSwitchSombre.setTextColor(getResources().getColor(R.color.marronclair));
        }


        //attach a listener to check for changes in state Sombre
        themeSwitchSombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Sombre is currently ON");
                    checkSombre = true;
                    theme_pref_okSombre.setVisibility(TextView.VISIBLE);
                    theme_pref_noSombre.setVisibility(TextView.GONE);
                    theme_pref_okSombre.setTextColor(getResources().getColor(R.color.windowBackground));
                    SharedPreferences prefs10 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs10.edit().putBoolean("themeSombre", true).apply();
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    recycler.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    contourHaut.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                    contourBas.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                    cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    themeText.setTextColor(getResources().getColor(R.color.windowBackground));
                    themeSwitchSombre.setTextColor(getResources().getColor(R.color.windowBackground));

                }else{
                    Log.e("PB", "Sombre is currently OFF");
                    checkSombre = false;
                    theme_pref_noSombre.setVisibility(TextView.VISIBLE);
                    theme_pref_okSombre.setVisibility(TextView.GONE);
                    theme_pref_noSombre.setTextColor(getResources().getColor(R.color.marronclair));
                    SharedPreferences prefs10 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs10.edit().putBoolean("themeSombre", false).apply();
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.windowBackground));
                    recycler.setBackgroundColor(getResources().getColor(R.color.windowBackground));
                    contourHaut.setBackgroundColor(getResources().getColor(R.color.windowBackground));
                    contourBas.setBackgroundColor(getResources().getColor(R.color.windowBackground));
                    cardView.setBackgroundColor(getResources().getColor(R.color.windowBackground));
                    themeText.setTextColor(getResources().getColor(R.color.marronclair));
                    themeSwitchSombre.setTextColor(getResources().getColor(R.color.marronclair));
                }

            }
        });




        return rootView;

    }



}