package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
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

import com.onesignal.OneSignal;


/**
 * A simple {@link Fragment} subclass.
 */
public class Preferences extends Fragment {

    private View rootView;

    boolean checkSombre;


    boolean check00a07;
    boolean check07a0930;
    boolean check0930a1130;
    boolean check1130a14;
    boolean check14a1630;
    boolean check1630a19;
    boolean check19a00;
    boolean checksamedi;
    boolean checkdimanche;




    public Preferences() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Le Pont Chaban Delmas");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("Les Notifications");

        rootView = inflater.inflate(R.layout.preferences, container, false);
        // Inflate the layout for this fragment

        //Prise en compte du mode sombre ou non
        SharedPreferences prefsTheme = getActivity().getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        checkSombre = prefsTheme.getBoolean("themeSombre", true);




        SharedPreferences prefs = getActivity().getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        check00a07 = prefs.getBoolean("notif00a07", true);
        check07a0930 = prefs.getBoolean("notif07a0930", true);
        check0930a1130 = prefs.getBoolean("notif0930a1130", true);
        check1130a14 = prefs.getBoolean("notif1130a14", true);
        check14a1630 = prefs.getBoolean("notif14a1630", true);
        check1630a19 = prefs.getBoolean("notif1630a19", true);
        check19a00 = prefs.getBoolean("notif19a00", true);
        checksamedi = prefs.getBoolean("notifsamedi", true);
        checkdimanche = prefs.getBoolean("notifdimanche", true);


        final Switch notificationSwitch00a07 = (Switch) rootView.findViewById(R.id.notifications00a07);
        final Switch notificationSwitch07a0930 = (Switch) rootView.findViewById(R.id.notifications07a0930);
        final Switch notificationSwitch0930a1130 = (Switch) rootView.findViewById(R.id.notifications0930a1130);
        final Switch notificationSwitch1130a14 = (Switch) rootView.findViewById(R.id.notifications1130a14);
        final Switch notificationSwitch14a1630 = (Switch) rootView.findViewById(R.id.notifications14a1630);
        final Switch notificationSwitch1630a19 = (Switch) rootView.findViewById(R.id.notifications1630a19);
        final Switch notificationSwitch19a00 = (Switch) rootView.findViewById(R.id.notifications19a00);
        final Switch notificationSwitchsamedi = (Switch) rootView.findViewById(R.id.notificationssamedi);
        final Switch notificationSwitchdimanche = (Switch) rootView.findViewById(R.id.notificationsdimanche);
        final TextView notif_pref_ok00a07 = (TextView) rootView.findViewById(R.id.notif_pref_ok_00a07);
        final TextView notif_pref_ok07a0930 = (TextView) rootView.findViewById(R.id.notif_pref_ok_07a0930);
        final TextView notif_pref_ok0930a1130 = (TextView) rootView.findViewById(R.id.notif_pref_ok_0930a1130);
        final TextView notif_pref_ok1130a14 = (TextView) rootView.findViewById(R.id.notif_pref_ok_1130a14);
        final TextView notif_pref_ok14a1630 = (TextView) rootView.findViewById(R.id.notif_pref_ok_14a1630);
        final TextView notif_pref_ok1630a19 = (TextView) rootView.findViewById(R.id.notif_pref_ok_1630a19);
        final TextView notif_pref_ok19a00 = (TextView) rootView.findViewById(R.id.notif_pref_ok_19a00);
        final TextView notif_pref_oksamedi = (TextView) rootView.findViewById(R.id.notif_pref_ok_samedi);
        final TextView notif_pref_okdimanche = (TextView) rootView.findViewById(R.id.notif_pref_ok_dimanche);
        final TextView notif_pref_no_00a07 = (TextView) rootView.findViewById(R.id.notif_pref_no_00a07);
        final TextView notif_pref_no_07a0930 = (TextView) rootView.findViewById(R.id.notif_pref_no_07a0930);
        final TextView notif_pref_no_0930a1130 = (TextView) rootView.findViewById(R.id.notif_pref_no_0930a1130);
        final TextView notif_pref_no_1130a14 = (TextView) rootView.findViewById(R.id.notif_pref_no_1130a14);
        final TextView notif_pref_no_14a1630 = (TextView) rootView.findViewById(R.id.notif_pref_no_14a1630);
        final TextView notif_pref_no_1630a19 = (TextView) rootView.findViewById(R.id.notif_pref_no_1630a19);
        final TextView notif_pref_no_19a00 = (TextView) rootView.findViewById(R.id.notif_pref_no_19a00);
        final TextView notif_pref_no_samedi = (TextView) rootView.findViewById(R.id.notif_pref_no_samedi);
        final TextView notif_pref_no_dimanche = (TextView) rootView.findViewById(R.id.notif_pref_no_dimanche);

        //Définition fond fenetre principale
        final LinearLayout linearLayout = rootView.findViewById(R.id.linearnotif);
        final RelativeLayout recycler = rootView.findViewById(R.id.notifrelative);
        final RelativeLayout recycler1 = rootView.findViewById(R.id.notifrelative1);
        final RelativeLayout recycler2 = rootView.findViewById(R.id.notifrelative2);
        final RelativeLayout recycler3 = rootView.findViewById(R.id.notifrelative3);
        final RelativeLayout recycler4 = rootView.findViewById(R.id.notifrelative4);
        final RelativeLayout recycler5 = rootView.findViewById(R.id.notifrelative5);
        final RelativeLayout recycler6= rootView.findViewById(R.id.notifrelative6);
        final RelativeLayout recycler7 = rootView.findViewById(R.id.notifrelative7);
        final RelativeLayout recycler8= rootView.findViewById(R.id.notifrelative8);

        final CardView card_view_07a0930 = rootView.findViewById(R.id.card_view_07a0930);
        final CardView card_view_00a07 = rootView.findViewById(R.id.card_view_00a07);
        final CardView card_view_0930a1130 = rootView.findViewById(R.id.card_view_0930a1130);
        final CardView card_view_19a00 = rootView.findViewById(R.id.card_view_19a00);
        final CardView card_view_1130a14 = rootView.findViewById(R.id.card_view_1130a14);
        final CardView card_view_1630a19 = rootView.findViewById(R.id.card_view_1630a19);
        final CardView card_view_14a1630 = rootView.findViewById(R.id.card_view_14a1630);
        final CardView card_view_samedi = rootView.findViewById(R.id.card_view_samedi);
        final CardView card_view_dimanche = rootView.findViewById(R.id.card_view_dimanche);

        final TextView d00a07text = rootView.findViewById(R.id.d00a07text);
        final TextView d07a0930text = rootView.findViewById(R.id.d07a0930text);
        final TextView d14a1630text = rootView.findViewById(R.id.d14a1630text);
        final TextView d19a00text = rootView.findViewById(R.id.d19a00text);
        final TextView d0930a1130text = rootView.findViewById(R.id.d0930a1130text);
        final TextView d1130a14text = rootView.findViewById(R.id.d1130a14text);
        final TextView d1630a19text = rootView.findViewById(R.id.d1630a19text);
        final TextView dimanchetext = rootView.findViewById(R.id.dimanchetext);
        final TextView sameditext = rootView.findViewById(R.id.sameditext);





        //set the switch to ON 00h à 07H00
        if (check00a07) {
            notificationSwitch00a07.setChecked(true);
            notif_pref_no_00a07.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitch00a07.setChecked(false);
            notif_pref_ok00a07.setVisibility(TextView.GONE);
        }

        //set the switch to ON 07h00 à 09h30
        if (check07a0930) {
            notificationSwitch07a0930.setChecked(true);
            notif_pref_no_07a0930.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitch07a0930.setChecked(false);
            notif_pref_ok07a0930.setVisibility(TextView.GONE);
        }
        //set the switch to ON 09h30 à 11h30
        if (check0930a1130) {
            notificationSwitch0930a1130.setChecked(true);
            notif_pref_no_0930a1130.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitch0930a1130.setChecked(false);
            notif_pref_ok0930a1130.setVisibility(TextView.GONE);
        }
        //set the switch to ON 11h30 à 14h
        if (check1130a14) {
            notificationSwitch1130a14.setChecked(true);
            notif_pref_no_1130a14.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitch1130a14.setChecked(false);
            notif_pref_ok1130a14.setVisibility(TextView.GONE);
        }
        //set the switch to ON 14h à 16h30
        if (check14a1630) {
            notificationSwitch14a1630.setChecked(true);
            notif_pref_no_14a1630.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitch14a1630.setChecked(false);
            notif_pref_ok14a1630.setVisibility(TextView.GONE);
        }
        //set the switch to ON 16h30 à 19h
        if (check1630a19) {
            notificationSwitch1630a19.setChecked(true);
            notif_pref_no_1630a19.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitch1630a19.setChecked(false);
            notif_pref_ok1630a19.setVisibility(TextView.GONE);
        }
        //set the switch to ON 19h à 00H
        if (check19a00) {
            notificationSwitch19a00.setChecked(true);
            notif_pref_no_19a00.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitch19a00.setChecked(false);
            notif_pref_ok19a00.setVisibility(TextView.GONE);
        }
        //set the switch to ON samedi
        if (checksamedi) {
            notificationSwitchsamedi.setChecked(true);
            notif_pref_no_samedi.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitchsamedi.setChecked(false);
            notif_pref_oksamedi.setVisibility(TextView.GONE);
        }
        //set the switch to ON dimanche
        if (checkdimanche) {
            notificationSwitchdimanche.setChecked(true);
            notif_pref_no_dimanche.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitchdimanche.setChecked(false);
            notif_pref_okdimanche.setVisibility(TextView.GONE);
        }


        //mode clair
        if(!checkSombre) {
            notif_pref_no_00a07.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_no_07a0930.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_no_0930a1130.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_no_19a00.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_no_1130a14.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_no_1630a19.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_no_14a1630.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_no_samedi.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_no_dimanche.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_ok00a07.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_ok07a0930.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_ok0930a1130.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_ok19a00.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_ok1130a14.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_ok1630a19.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_ok14a1630.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_oksamedi.setTextColor(getResources().getColor(R.color.marronclair));
            notif_pref_okdimanche.setTextColor(getResources().getColor(R.color.marronclair));

            linearLayout.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler1.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler2.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler3.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler4.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler5.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler6.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler7.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            recycler8.setBackgroundColor(getResources().getColor(R.color.windowBackground));

            card_view_00a07.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            card_view_07a0930.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            card_view_0930a1130.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            card_view_19a00.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            card_view_1130a14.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            card_view_1630a19.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            card_view_14a1630.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            card_view_samedi.setBackgroundColor(getResources().getColor(R.color.windowBackground));
            card_view_dimanche.setBackgroundColor(getResources().getColor(R.color.windowBackground));

            notificationSwitch00a07.setTextColor(getResources().getColor(R.color.marronclair));
            notificationSwitch07a0930.setTextColor(getResources().getColor(R.color.marronclair));
            notificationSwitch0930a1130.setTextColor(getResources().getColor(R.color.marronclair));
            notificationSwitch19a00.setTextColor(getResources().getColor(R.color.marronclair));
            notificationSwitch1130a14.setTextColor(getResources().getColor(R.color.marronclair));
            notificationSwitch1630a19.setTextColor(getResources().getColor(R.color.marronclair));
            notificationSwitch14a1630.setTextColor(getResources().getColor(R.color.marronclair));
            notificationSwitchdimanche.setTextColor(getResources().getColor(R.color.marronclair));
            notificationSwitchsamedi.setTextColor(getResources().getColor(R.color.marronclair));


        }

        //attach a listener to check for changes in state 00h à 07h00
        notificationSwitch00a07.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    check00a07 = true;
                    OneSignal.setSubscription(true);
                    notif_pref_ok00a07.setVisibility(TextView.VISIBLE);
                    notif_pref_no_00a07.setVisibility(TextView.GONE);
                    OneSignal.sendTag("00a07", "00a07");
                    SharedPreferences prefs1 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs1.edit().putBoolean("notif00a07", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    check00a07 = false;
                    notif_pref_no_00a07.setVisibility(TextView.VISIBLE);
                    notif_pref_ok00a07.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("00a07");
                    SharedPreferences prefs1 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs1.edit().putBoolean("notif00a07", false).apply();
                }

            }
        });

        //attach a listener to check for changes in state 07h à 09h30
        notificationSwitch07a0930.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    check07a0930 = true;
                    OneSignal.setSubscription(true);
                    notif_pref_ok07a0930.setVisibility(TextView.VISIBLE);
                    notif_pref_no_07a0930.setVisibility(TextView.GONE);
                    OneSignal.sendTag("07a0930", "07a0930");
                    SharedPreferences prefs2 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs2.edit().putBoolean("notif07a0930", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    check07a0930 = false;
                    notif_pref_no_07a0930.setVisibility(TextView.VISIBLE);
                    notif_pref_ok07a0930.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("07a0930");
                    SharedPreferences prefs2 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs2.edit().putBoolean("notif07a0930", false).apply();
                }

            }
        });

        //attach a listener to check for changes in state 09h30 à 11h30

        notificationSwitch0930a1130.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    check0930a1130 = true;
                    OneSignal.setSubscription(true);
                    notif_pref_ok0930a1130.setVisibility(TextView.VISIBLE);
                    notif_pref_no_0930a1130.setVisibility(TextView.GONE);
                    OneSignal.sendTag("0930a1130", "0930a1130");
                    SharedPreferences prefs3 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs3.edit().putBoolean("notif0930a1130", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    check0930a1130 = false;
                    notif_pref_no_0930a1130.setVisibility(TextView.VISIBLE);
                    notif_pref_ok0930a1130.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("0930a1130");
                    SharedPreferences prefs3 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs3.edit().putBoolean("notif0930a1130", false).apply();
                }

            }
        });

        //attach a listener to check for changes in state 11h30 à 14h

        notificationSwitch1130a14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    check1130a14 = true;
                    OneSignal.setSubscription(true);
                    notif_pref_ok1130a14.setVisibility(TextView.VISIBLE);
                    notif_pref_no_1130a14.setVisibility(TextView.GONE);
                    OneSignal.sendTag("1130a14", "1130a14");
                    SharedPreferences prefs4 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs4.edit().putBoolean("notif1130a14", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    check1130a14 = false;
                    notif_pref_no_1130a14.setVisibility(TextView.VISIBLE);
                    notif_pref_ok1130a14.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("1130a14");
                    SharedPreferences prefs4 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs4.edit().putBoolean("notif1130a14", false).apply();
                }

            }
        });

        //attach a listener to check for changes in state 14h à 16h30

        notificationSwitch14a1630.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    check14a1630 = true;
                    OneSignal.setSubscription(true);
                    notif_pref_ok14a1630.setVisibility(TextView.VISIBLE);
                    notif_pref_no_14a1630.setVisibility(TextView.GONE);
                    OneSignal.sendTag("14a1630", "14a1630");
                    SharedPreferences prefs5 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs5.edit().putBoolean("notif14a1630", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    check14a1630 = false;
                    notif_pref_no_14a1630.setVisibility(TextView.VISIBLE);
                    notif_pref_ok14a1630.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("14a1630");
                    SharedPreferences prefs5 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs5.edit().putBoolean("notif14a1630", false).apply();
                }

            }
        });

        //attach a listener to check for changes in state 16h30 à 19h

        notificationSwitch1630a19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    check1630a19 = true;
                    OneSignal.setSubscription(true);
                    notif_pref_ok1630a19.setVisibility(TextView.VISIBLE);
                    notif_pref_no_1630a19.setVisibility(TextView.GONE);
                    OneSignal.sendTag("1630a19", "1630a19");
                    SharedPreferences prefs6 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs6.edit().putBoolean("notif1630a19", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    check1630a19 = false;
                    notif_pref_no_1630a19.setVisibility(TextView.VISIBLE);
                    notif_pref_ok1630a19.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("1630a19");
                    SharedPreferences prefs6 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs6.edit().putBoolean("notif1630a19", false).apply();
                }

            }
        });

        //attach a listener to check for changes in state 19h à 00H
        notificationSwitch19a00.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    check19a00 = true;
                    OneSignal.setSubscription(true);
                    notif_pref_ok19a00.setVisibility(TextView.VISIBLE);
                    notif_pref_no_19a00.setVisibility(TextView.GONE);
                    OneSignal.sendTag("19a00", "19a00");
                    SharedPreferences prefs7 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs7.edit().putBoolean("notif19a00", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    check19a00 = false;
                    notif_pref_no_19a00.setVisibility(TextView.VISIBLE);
                    notif_pref_ok19a00.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("19a00");
                    SharedPreferences prefs7 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs7.edit().putBoolean("notif19a00", false).apply();
                }

            }
        });
        //attach a listener to check for changes in state Samedi

        notificationSwitchsamedi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    checksamedi = true;
                    OneSignal.setSubscription(true);
                    notif_pref_oksamedi.setVisibility(TextView.VISIBLE);
                    notif_pref_no_samedi.setVisibility(TextView.GONE);
                    OneSignal.sendTag("samedi", "samedi");
                    SharedPreferences prefs8 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs8.edit().putBoolean("notifsamedi", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    checksamedi = false;
                    notif_pref_no_samedi.setVisibility(TextView.VISIBLE);
                    notif_pref_oksamedi.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("samedi");
                    SharedPreferences prefs8 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs8.edit().putBoolean("notifsamedi", false).apply();
                }

            }
        });

        //attach a listener to check for changes in state Dimanche

        notificationSwitchdimanche.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    checkdimanche = true;
                    OneSignal.setSubscription(true);
                    notif_pref_okdimanche.setVisibility(TextView.VISIBLE);
                    notif_pref_no_dimanche.setVisibility(TextView.GONE);
                    OneSignal.sendTag("dimanche", "dimanche");
                    SharedPreferences prefs9 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs9.edit().putBoolean("notifdimanche", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    checkdimanche = false;
                    notif_pref_no_dimanche.setVisibility(TextView.VISIBLE);
                    notif_pref_okdimanche.setVisibility(TextView.GONE);
                    OneSignal.deleteTag("dimanche");
                    SharedPreferences prefs9 = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs9.edit().putBoolean("notifdimanche", false).apply();
                }

            }
        });

        return rootView;

    }



}