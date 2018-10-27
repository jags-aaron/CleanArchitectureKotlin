package com.n3k0.amplemindcleanarchitecture.platform.view.main

import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.presentation.MainItemPresenter

sealed class MainIntents {
    object InitialIntent: MainIntents()
    data class GetCountriesIntent(val isGettingData: Boolean): MainIntents()
    data class ShowDetailIntent(val country: Country): MainIntents()
    data class ShowErrorIntent(val failure: Exception): MainIntents()
    data class ShowSuccessIntent(val countries: List<MainItemPresenter>): MainIntents()
}