package com.n3k0.amplemindcleanarchitecture.data

import com.n3k0.amplemindcleanarchitecture.source.boundary.CountriesLocalSource
import com.n3k0.amplemindcleanarchitecture.source.boundary.CountriesRemoteSource
import com.n3k0.amplemindcleanarchitecture.data.boundary.CountryRepository
import com.n3k0.amplemindcleanarchitecture.data.common.Either
import com.n3k0.amplemindcleanarchitecture.data.model.Country

class CountryRepositoryImp(
    private val remoteSource: CountriesRemoteSource,
    private val localSource: CountriesLocalSource
) : CountryRepository {

    private val isOnline = true

    override fun getCountries(): Either<Exception, List<Country>> {
        return try {
            if (isOnline) {
                Either.Right(remoteSource.getCountries())
            } else {
                Either.Right(localSource.getCountries())
            }
        } catch (exception: Exception) {
            Either.Left(exception)
        }
    }
}