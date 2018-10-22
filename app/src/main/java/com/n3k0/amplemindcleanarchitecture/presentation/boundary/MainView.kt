package com.n3k0.amplemindcleanarchitecture.presentation.boundary

import com.n3k0.amplemindcleanarchitecture.data.model.Country

interface MainView {
    fun showError(error: String)
    fun showList(itemCountries: List<ItemPresenter>)
    fun itemListClick(country: Country)
}