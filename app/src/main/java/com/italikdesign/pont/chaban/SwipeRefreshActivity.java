package com.italikdesign.pont.chaban;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by italikdesign on 31/05/2016.
 */
public abstract class SwipeRefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.fragment_main);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
    }

    @Override public void setContentView(int layoutResID) {
        View v = getLayoutInflater().inflate(layoutResID, refreshLayout, false);
        setContentView(v);
    }

    @Override public void setContentView(View view) {
        setContentView(view, view.getLayoutParams());
    }

    @Override public void setContentView(View view, ViewGroup.LayoutParams params) {
        refreshLayout.addView(view, params);
        initSwipeOptions();
    }

    private void initSwipeOptions() {
        refreshLayout.setOnRefreshListener(this);

        disableSwipe();
    }


    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void showSwipeProgress() {
        refreshLayout.setRefreshing(true);
    }

    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void hideSwipeProgress() {
        refreshLayout.setRefreshing(false);
    }

    /**
     * Enables swipe gesture
     */
    public void enableSwipe() {
        refreshLayout.setEnabled(true);
    }

    /**
     * Disables swipe gesture. It prevents manual gestures but keeps the option tu show
     * refreshing programatically.
     */
    public void disableSwipe() {
        refreshLayout.setEnabled(false);
    }

    /**
     * It must be overriden by parent classes if manual swipe is enabled.
     */
    @Override public void onRefresh() {
        // Empty implementation
    }
}