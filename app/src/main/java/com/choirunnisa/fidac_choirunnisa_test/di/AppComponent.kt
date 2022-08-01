package com.choirunnisa.fidac_choirunnisa_test.di

import android.app.Application
import com.choirunnisa.fidac_choirunnisa_test.FindacApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    ActivityBuilder::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }
    fun inject(application : FindacApplication)
}