package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */


import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;


import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swipeLayout;


    private AdView adView;
    private RecyclerView recyclerView;
    public static ListFeedAdapter lfa;
    private View rootView;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ArrayList<Feed> feeds = ContainerData.getFeeds();
        for (Feed feed : feeds) {
            Log.e("MainActivity", feed.toString());
        }




        //RecyclerView
        lfa = new ListFeedAdapter(getActivity(), feeds);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listFeed);
        recyclerView.setAdapter(lfa);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(llm);

        adView = (AdView) rootView.findViewById(R.id.adViewCardItem);


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorAccent));


        return rootView;


    }


    @Override
    public void onRefresh() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ArrayList<Feed> feeds = ContainerData.getFeeds();
                for (Feed feed : feeds) {
                    Log.e("MainActivity", feed.toString());
                }
                //RecyclerView
                lfa = new ListFeedAdapter(getActivity(), feeds);
                recyclerView = (RecyclerView) rootView.findViewById(R.id.listFeed);
                recyclerView.setAdapter(lfa);
                final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(llm);
                swipeLayout.setRefreshing(false);
            }
        }, 5000);


    }





}
