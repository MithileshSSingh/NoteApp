package com.example.basicmvp.notes;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

/**
 * Created by mithilesh on 8/30/16.
 */
public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
