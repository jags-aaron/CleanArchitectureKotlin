package com.n3k0.amplemindcleanarchitecture.domain.common

import com.n3k0.amplemindcleanarchitecture.data.boundary.CountryRepository
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.domain.CountriesToItemPresenterUseCase
import com.n3k0.amplemindcleanarchitecture.domain.GetCountriesUseCase
import com.n3k0.amplemindcleanarchitecture.domain.boundary.Executor
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory

class UseCaseFactoryImp(
    val executor: Executor,
    private val repository: CountryRepository
) : UseCaseFactory {

    override fun getCountries(): GetCountriesUseCase {
        return GetCountriesUseCase(executor, repository)
    }

    override fun convertCountriesToItemPresenter(
        countries: List<Country>,
        useCaseFactory: UseCaseFactory
    ): CountriesToItemPresenterUseCase {
        return CountriesToItemPresenterUseCase(executor, countries, useCaseFactory)
    }


}