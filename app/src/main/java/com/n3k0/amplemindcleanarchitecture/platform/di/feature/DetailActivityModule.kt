package com.n3k0.amplemindcleanarchitecture.platform.di.feature

import com.n3k0.amplemindcleanarchitecture.platform.di.scope.PerActivity
import com.n3k0.amplemindcleanarchitecture.platform.view.detail.DetailActivity
import com.n3k0.amplemindcleanarchitecture.presentation.DetailActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailActivityModule {

    @Provides
    @PerActivity
    internal fun providePresenter(detailActivity: DetailActivity): DetailActivityPresenter {
        val flag = detailActivity.intent.getStringExtra(DetailActivity.COUNTRY_IMAGE)
        val name = detailActivity.intent.getStringExtra(DetailActivity.COUNTRY_NAME)
        return DetailActivityPresenter(detailActivity, flag, name)
    }

}
