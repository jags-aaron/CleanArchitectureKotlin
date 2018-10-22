package com.n3k0.amplemindcleanarchitecture.data.boundary

import com.n3k0.amplemindcleanarchitecture.data.common.Either
import com.n3k0.amplemindcleanarchitecture.data.model.Country

interface CountryRepository {
    fun getCountries (): Either<Exception, List<Country>>
}