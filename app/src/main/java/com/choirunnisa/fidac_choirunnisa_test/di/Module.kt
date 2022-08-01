package com.choirunnisa.fidac_choirunnisa_test.di

import com.choirunnisa.fidac_choirunnisa_test.ui.ListProductActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{
    @ContributesAndroidInjector
    internal abstract fun bindListProductActivity(): ListProductActivity
}