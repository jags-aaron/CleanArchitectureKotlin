package com.n3k0.amplemindcleanarchitecture.platform.di

import com.n3k0.amplemindcleanarchitecture.platform.di.feature.DetailActivityModule
import com.n3k0.amplemindcleanarchitecture.platform.di.feature.MainActivityModule
import com.n3k0.amplemindcleanarchitecture.platform.di.scope.PerActivity
import com.n3k0.amplemindcleanarchitecture.platform.view.detail.DetailActivity
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @PerActivity
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    @PerActivity
    internal abstract fun contributeDetailActivity(): DetailActivity

}
