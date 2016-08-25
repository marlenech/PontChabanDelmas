package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;


import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.italikdesign.inappbilling.util.IabHelper;
import com.italikdesign.inappbilling.util.IabResult;
import com.italikdesign.inappbilling.util.Inventory;
import com.italikdesign.inappbilling.util.Purchase;
import com.italikdesign.pont.chaban.MainActivity;


import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragmentDisAd extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swipeLayout;



    private RecyclerView recyclerView;
    public static ListFeedAdapter lfa;
    private View rootView;


    public MainFragmentDisAd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        StartProgress();

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        return rootView;



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {




        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorAccent));


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
        }, 2000);


    }

    public void StartProgress() {
        new AsyncProgressBar().execute();
    }

    private class AsyncProgressBar extends AsyncTask<Void, Void, Void> {




        protected ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Chargement des données...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //duration of progressbar
            SystemClock.sleep(1000);

            return null;
        }



        @Override
        protected void onPostExecute(Void useless) {
            ArrayList<Feed> feeds = ContainerData.getFeeds();
            for (Feed feed : feeds) {
                Log.e("MainActivity", feed.toString());
            }
            //RecyclerView
            lfa = new ListFeedAdapter(getActivity(), feeds);
            recyclerView = (RecyclerView) getActivity().findViewById(R.id.listFeed);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(lfa);
            final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(llm);


            dialog.dismiss();
        }
    }

}
