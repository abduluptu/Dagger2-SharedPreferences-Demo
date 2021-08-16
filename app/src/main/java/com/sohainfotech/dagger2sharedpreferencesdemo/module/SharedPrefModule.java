package com.sohainfotech.dagger2sharedpreferencesdemo.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//Step: 2

@Module
public class SharedPrefModule {

    //declare Context
    private Context context;

    //initialize context inside constructor
    public SharedPrefModule(Context context) {
        this.context = context;
    }

    //get context with singleton
    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    //get SharedPreferences object with singleton
    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
