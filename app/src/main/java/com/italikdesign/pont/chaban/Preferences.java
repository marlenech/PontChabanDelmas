package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.onesignal.OneSignal;


/**
 * A simple {@link Fragment} subclass.
 */
public class Preferences extends Fragment {

    private View rootView;


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


        Switch notificationSwitch00a07 = (Switch) rootView.findViewById(R.id.notifications00a07);
        Switch notificationSwitch07a0930 = (Switch) rootView.findViewById(R.id.notifications07a0930);
        Switch notificationSwitch0930a1130 = (Switch) rootView.findViewById(R.id.notifications0930a1130);
        Switch notificationSwitch1130a14 = (Switch) rootView.findViewById(R.id.notifications1130a14);
        Switch notificationSwitch14a1630 = (Switch) rootView.findViewById(R.id.notifications14a1630);
        Switch notificationSwitch1630a19 = (Switch) rootView.findViewById(R.id.notifications1630a19);
        Switch notificationSwitch19a00 = (Switch) rootView.findViewById(R.id.notifications19a00);
        Switch notificationSwitchsamedi = (Switch) rootView.findViewById(R.id.notificationssamedi);
        Switch notificationSwitchdimanche = (Switch) rootView.findViewById(R.id.notificationsdimanche);
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