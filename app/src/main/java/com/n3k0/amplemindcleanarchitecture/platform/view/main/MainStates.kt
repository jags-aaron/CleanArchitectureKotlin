package com.n3k0.amplemindcleanarchitecture.platform.view.main

import com.n3k0.amplemindcleanarchitecture.data.model.Country

data class MainStates(
    val progress: Boolean = false,
    val success: List<Country> = emptyList(),
    val error: Exception = Exception(""),
    val country: Country = Country()
)