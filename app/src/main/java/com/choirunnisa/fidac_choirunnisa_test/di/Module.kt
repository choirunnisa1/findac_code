package com.choirunnisa.fidac_choirunnisa_test.di

import com.choirunnisa.fidac_choirunnisa_test.ui.DetailProductActivity
import com.choirunnisa.fidac_choirunnisa_test.ui.DetailUserActivity
import com.choirunnisa.fidac_choirunnisa_test.ui.ListProductActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{
    @ContributesAndroidInjector
    internal abstract fun bindListProductActivity(): ListProductActivity

    @ContributesAndroidInjector
    internal abstract fun bindDetailUserActivity(): DetailUserActivity

    @ContributesAndroidInjector
    internal abstract fun bindDetailProductActivity(): DetailProductActivity
}