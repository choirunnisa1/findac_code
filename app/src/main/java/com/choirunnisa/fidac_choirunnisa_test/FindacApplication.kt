package com.choirunnisa.fidac_choirunnisa_test

import android.app.Activity
import android.app.Application
import com.choirunnisa.fidac_choirunnisa_test.di.AppComponent
import com.choirunnisa.fidac_choirunnisa_test.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

import javax.inject.Inject

class FindacApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> = androidInjector

}