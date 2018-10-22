package com.n3k0.amplemindcleanarchitecture.domain

import com.n3k0.amplemindcleanarchitecture.domain.common.UseCase
import com.n3k0.amplemindcleanarchitecture.domain.boundary.Executor
import com.n3k0.amplemindcleanarchitecture.data.boundary.CountryRepository
import com.n3k0.amplemindcleanarchitecture.data.common.Either
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import java.lang.Exception

class GetCountriesUseCase(
    executor: Executor,
    private val countryRepository: CountryRepository
) : UseCase(executor) {

    fun execute(onResult: (Either<Exception, List<Country>>) -> Unit) {
        asyncExecute {
            // Asynchronous process
            val response = countryRepository.getCountries()
            uiExecute {
                // Synchronous process
                onResult(response)
            }
        }
    }
}