package com.n3k0.amplemindcleanarchitecture.source.boundary

import com.n3k0.amplemindcleanarchitecture.data.model.Country

interface CountriesLocalSource {
    fun getCountries(): List<Country>
}