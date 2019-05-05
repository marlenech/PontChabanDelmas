package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;


import android.os.SystemClock;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


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

        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.ouvert);

        StartProgress();

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        return rootView;



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {




        swipeLayout = rootView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorAccent));


    }


    @Override
    public void onRefresh() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Utils.connectivity(getActivity())) {
                    if (isAdded()) {

                        ArrayList<Feed> feeds = ContainerData.getFeeds();
                        //RecyclerView
                        lfa = new ListFeedAdapter(getActivity(), feeds);
                        recyclerView = rootView.findViewById(R.id.listFeed);
                        recyclerView.setAdapter(lfa);
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setLayoutManager(llm);
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.ouvert);

                    } else {
                        Toast.makeText(getActivity(), "Vous devez être connecté à internet pour recevoir les données.", Toast.LENGTH_LONG).show();
                    }
                }
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
            if(Utils.connectivity(getActivity())) {
                if (isAdded()) {
                    ArrayList<Feed> feeds = ContainerData.getFeeds();

                    //RecyclerView
                    lfa = new ListFeedAdapter(getActivity(), feeds);
                    recyclerView = getActivity().findViewById(R.id.listFeed);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(lfa);
                    final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setLayoutManager(llm);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Vous devez être connecté à internet pour recevoir les données.", Toast.LENGTH_LONG).show();
                }
            }
            dialog.dismiss();


        }
    }

}
