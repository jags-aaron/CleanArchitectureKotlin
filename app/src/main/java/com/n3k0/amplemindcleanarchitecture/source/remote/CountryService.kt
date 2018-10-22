package com.n3k0.amplemindcleanarchitecture.source.remote

import com.n3k0.amplemindcleanarchitecture.source.entity.CountryEntity
import retrofit2.Call
import retrofit2.http.*

interface CountryService {

    @GET("rest/v2/all")
    fun getCountries(): Call<List<CountryEntity>>

}
