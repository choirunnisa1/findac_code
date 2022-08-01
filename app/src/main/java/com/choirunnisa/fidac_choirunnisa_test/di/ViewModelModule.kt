package com.choirunnisa.fidac_choirunnisa_test.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.ListProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListProductViewModel::class)
    abstract fun bindListProductViewModel(viewModel: ListProductViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}