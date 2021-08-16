package com.sohainfotech.dagger2sharedpreferencesdemo.component;

import com.sohainfotech.dagger2sharedpreferencesdemo.activity.MainActivity;
import com.sohainfotech.dagger2sharedpreferencesdemo.module.SharedPrefModule;

import javax.inject.Singleton;

import dagger.Component;

//Step: 3

@Singleton
@Component(modules = {SharedPrefModule.class})
public interface MyComponent {

    //provide consumer inside inject(), where we want to use/consume dependency/object
    void inject(MainActivity activity);

}
