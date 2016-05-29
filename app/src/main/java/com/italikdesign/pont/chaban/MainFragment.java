package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private AdView adView;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        ArrayList<Feed> feeds = ContainerData.getFeeds();
        for (Feed feed : feeds) {
            Log.e("MainActivity", feed.toString());
        }
        ListFeedAdapter lfa = new ListFeedAdapter(getActivity(), feeds);
        ((ListView) rootView.findViewById(R.id.listFeed)).setAdapter(lfa);

        adView = (AdView) rootView.findViewById(R.id.adViewCardItem);


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Inflate the layout for this fragment
        return rootView;
    }


}

