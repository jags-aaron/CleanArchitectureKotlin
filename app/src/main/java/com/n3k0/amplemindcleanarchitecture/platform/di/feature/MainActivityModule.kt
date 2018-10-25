package com.n3k0.amplemindcleanarchitecture.platform.di.feature

import com.n3k0.amplemindcleanarchitecture.data.CountryRepositoryImp
import com.n3k0.amplemindcleanarchitecture.source.boundary.CountriesLocalSource
import com.n3k0.amplemindcleanarchitecture.source.boundary.CountriesRemoteSource
import com.n3k0.amplemindcleanarchitecture.domain.boundary.Executor
import com.n3k0.amplemindcleanarchitecture.data.boundary.CountryRepository
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import com.n3k0.amplemindcleanarchitecture.domain.common.UseCaseFactoryImp
import com.n3k0.amplemindcleanarchitecture.platform.di.scope.PerActivity
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainActivity
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityViewModel
import com.n3k0.amplemindcleanarchitecture.presentation.executor.CoroutinesExecutor
import com.n3k0.amplemindcleanarchitecture.source.local.CountriesLocalDataSourceImp
import com.n3k0.amplemindcleanarchitecture.source.mapper.CountryMapper
import com.n3k0.amplemindcleanarchitecture.source.remote.CountriesRemoteDataSourceImp
import com.n3k0.amplemindcleanarchitecture.source.remote.CountryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainActivityModule {

    @Provides
    internal fun provideViewModel(useCaseFactory: UseCaseFactory): MainActivityViewModel {
        return MainActivityViewModel(useCaseFactory)
    }

    @Provides
    internal fun provideExecutor(): Executor {
        return CoroutinesExecutor()
    }

    @Provides
    internal fun provideUseCaseFactory(executor: Executor, repository: CountryRepository): UseCaseFactory {
        return UseCaseFactoryImp(executor, repository)
    }

    @Provides
    internal fun provideMockRespository(remoteSource: CountriesRemoteSource, localSource: CountriesLocalSource): CountryRepository {
        return CountryRepositoryImp(remoteSource, localSource)
    }

    @Provides
    internal fun provideRemoteService(countryService: CountryService, mapper: CountryMapper): CountriesRemoteSource {
        return CountriesRemoteDataSourceImp(countryService, mapper)
    }

    @Provides
    internal fun provideLocalService(): CountriesLocalSource {
        return CountriesLocalDataSourceImp()
    }

    @Provides
    internal fun provideMapper(): CountryMapper {
        return CountryMapper()
    }

    @Provides
    internal fun provideCountryApi(retrofit: Retrofit): CountryService {
        return retrofit.create(CountryService::class.java)
    }
}
