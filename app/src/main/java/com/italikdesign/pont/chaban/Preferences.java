package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.onesignal.OneSignal;


/**
 * A simple {@link Fragment} subclass.
 */
public class Preferences extends Fragment {

    private View rootView;


    boolean check;




    public Preferences() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.preferences, container, false);
        // Inflate the layout for this fragment



        SharedPreferences prefs = getActivity().getSharedPreferences(
                "com.italikdesign.pont.chaban", 0);
        check = prefs.getBoolean("notif", true);

        Switch notificationSwitch = (Switch) rootView.findViewById(R.id.notifications);
        final TextView notif_pref_ok = (TextView) rootView.findViewById(R.id.notif_pref_ok);
        final TextView notif_pref_no = (TextView) rootView.findViewById(R.id.notif_pref_no);
        //set the switch to ON
        if (check) {
            notificationSwitch.setChecked(true);
            notif_pref_no.setVisibility(TextView.GONE);
        }
        else {
            notificationSwitch.setChecked(false);
            notif_pref_ok.setVisibility(TextView.GONE);
        }

        //attach a listener to check for changes in state
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    Log.e("PB", "Notifications is currently ON");
                    check = true;
                    OneSignal.setSubscription(true);
                    notif_pref_ok.setVisibility(TextView.VISIBLE);
                    notif_pref_no.setVisibility(TextView.GONE);
                    SharedPreferences prefs = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs.edit().putBoolean("notif", true).apply();

                }else{
                    Log.e("PB", "Notifications is currently OFF");
                    check = false;
                    OneSignal.setSubscription(false);
                    notif_pref_no.setVisibility(TextView.VISIBLE);
                    notif_pref_ok.setVisibility(TextView.GONE);
                    SharedPreferences prefs = getActivity().getBaseContext().getSharedPreferences(
                            "com.italikdesign.pont.chaban", 0);
                    prefs.edit().putBoolean("notif", false).apply();
                }

            }
        });







        return rootView;

    }



}
