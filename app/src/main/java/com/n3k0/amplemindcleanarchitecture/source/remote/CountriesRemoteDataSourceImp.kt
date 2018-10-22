package com.n3k0.amplemindcleanarchitecture.source.remote

import com.n3k0.amplemindcleanarchitecture.data.model.Country

import com.n3k0.amplemindcleanarchitecture.source.boundary.CountriesRemoteSource
import com.n3k0.amplemindcleanarchitecture.source.exception.NetworkConnectionException
import com.n3k0.amplemindcleanarchitecture.source.exception.ServiceException
import com.n3k0.amplemindcleanarchitecture.source.mapper.CountryMapper
import java.io.IOException

class CountriesRemoteDataSourceImp(
    private val service: CountryService,
    private val mapper: CountryMapper
) : CountriesRemoteSource {

    @Throws(Exception::class)
    override fun getCountries(): List<Country> {
        try {
            val response = service.getCountries().execute()
            if (response.isSuccessful) {
                return mapper.transform(response.body().orEmpty())
            } else {
                throw ServiceException()
            }
        } catch (exception: IOException) {
            throw NetworkConnectionException()
        }
    }

}