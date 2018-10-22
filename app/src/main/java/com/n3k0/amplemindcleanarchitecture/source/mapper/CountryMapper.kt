package com.n3k0.amplemindcleanarchitecture.source.mapper

import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.source.entity.CountryEntity

class CountryMapper {

    fun transform(countryEntityList: List<CountryEntity>): List<Country> {
        val countryList: MutableList<Country> = mutableListOf()

        for (entity in countryEntityList) {
            countryList.add(transform(entity))
        }

        return countryList
    }

    fun transform(countryEntity: CountryEntity): Country {
        return Country(
            countryEntity.id,
            countryEntity.name,
            countryEntity.flag
        )
    }
}