package com.n3k0.amplemindcleanarchitecture

import com.n3k0.amplemindcleanarchitecture.data.model.Country

fun mockCountry(): Country {
    return Country(0, "mock name", "mock flag")
}

fun getCountries(): List<Country> {
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