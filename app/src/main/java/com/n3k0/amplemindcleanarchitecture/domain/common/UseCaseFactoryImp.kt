package com.n3k0.amplemindcleanarchitecture.domain.common

import com.n3k0.amplemindcleanarchitecture.data.boundary.CountryRepository
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
}