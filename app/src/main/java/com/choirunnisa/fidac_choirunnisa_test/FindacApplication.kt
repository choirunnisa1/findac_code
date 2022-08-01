package com.choirunnisa.fidac_choirunnisa_test

import android.app.Activity
import android.app.Application
import com.choirunnisa.fidac_choirunnisa_test.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class FindacApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val component = DaggerAppComponent.builder.application(this).build()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector


}