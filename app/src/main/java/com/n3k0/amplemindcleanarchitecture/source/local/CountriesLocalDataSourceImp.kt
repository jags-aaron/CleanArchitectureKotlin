package com.n3k0.amplemindcleanarchitecture.source.local

import com.n3k0.amplemindcleanarchitecture.data.model.Country

import com.n3k0.amplemindcleanarchitecture.source.boundary.CountriesLocalSource

class CountriesLocalDataSourceImp : CountriesLocalSource {

    @Throws(Exception::class)
    override fun getCountries(): List<Country> {
        val demo: MutableList<Country> = mutableListOf()

        for (elem in 1..20) {
            demo.add(
                Country(
                    elem,
                    "Name from local data $elem",
                    "flag from local data $elem"
                )
            )
        }

        return demo
    }
}