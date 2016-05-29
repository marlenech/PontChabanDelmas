package com.italikdesign.pont.chaban;

import android.app.Application;
import android.content.Context;

/**
 * Created by italikdesign on 29/05/2016.
 *
 * For getRessources to call in a static context
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}