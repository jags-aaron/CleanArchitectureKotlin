package com.n3k0.amplemindcleanarchitecture.source.entity

import com.google.gson.annotations.SerializedName

data class CountryEntity(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("capital")
        val capital: String?,
        @SerializedName("region")
        val region: String?,
        @SerializedName("flag")
        val flag: String?
)