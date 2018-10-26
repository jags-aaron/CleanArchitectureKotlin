package com.n3k0.amplemindcleanarchitecture.platform.view.main

import com.n3k0.amplemindcleanarchitecture.data.model.Country

sealed class MainIntents {
    object InitialIntent: MainIntents()
    data class GetCountriesIntent(val isGettingData: Boolean): MainIntents()
    data class ShowDetailIntent(val country: Country): MainIntents()
}