package com.n3k0.amplemindcleanarchitecture.platform.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Dagger module which provides ViewModel instances.
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //Add concrete ViewModel classes here
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun providesLoginViewModel(mainViewModel: MainActivityViewModel): ViewModel
}
