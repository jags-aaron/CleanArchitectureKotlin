package com.n3k0.amplemindcleanarchitecture.platform.dinodagger.feature

import com.n3k0.amplemindcleanarchitecture.data.CountryRepositoryImp
import com.n3k0.amplemindcleanarchitecture.data.boundary.CountryRepository
import com.n3k0.amplemindcleanarchitecture.domain.boundary.Executor
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import com.n3k0.amplemindcleanarchitecture.domain.common.UseCaseFactoryImp
import com.n3k0.amplemindcleanarchitecture.platform.dinodagger.app.CompositionRoot
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityPresenter
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.MainView
import com.n3k0.amplemindcleanarchitecture.presentation.executor.CoroutinesExecutor
import com.n3k0.amplemindcleanarchitecture.source.boundary.CountriesLocalSource
import com.n3k0.amplemindcleanarchitecture.source.boundary.CountriesRemoteSource
import com.n3k0.amplemindcleanarchitecture.source.local.CountriesLocalDataSourceImp
import com.n3k0.amplemindcleanarchitecture.source.mapper.CountryMapper
import com.n3k0.amplemindcleanarchitecture.source.remote.CountriesRemoteDataSourceImp
import com.n3k0.amplemindcleanarchitecture.source.remote.CountryService

class MainActivityComposition(val compositionRoot: CompositionRoot) {

    fun getPresenter(view: MainView): MainActivityPresenter {
        return MainActivityPresenter(view, provideUseCaseFactory())
    }

    /* ---------------------- PROVIDERS -------------------------*/

    private fun provideExecutor(): Executor {
        return CoroutinesExecutor()
    }

    private fun provideUseCaseFactory(): UseCaseFactory {
        return UseCaseFactoryImp(provideExecutor(), provideMockRepository())
    }

    private fun provideMockRepository(): CountryRepository {
        return CountryRepositoryImp(provideRemoteService(), provideLocalService())
    }

    private fun provideRemoteService(): CountriesRemoteSource {
        return CountriesRemoteDataSourceImp(provideCountryApi(), provideMapper())
    }

    private fun provideLocalService(): CountriesLocalSource {
        return CountriesLocalDataSourceImp()
    }

    private fun provideMapper(): CountryMapper {
        return CountryMapper()
    }

    private fun provideCountryApi(): CountryService {
        return compositionRoot.provideRetrofit().create(CountryService::class.java)
    }

}