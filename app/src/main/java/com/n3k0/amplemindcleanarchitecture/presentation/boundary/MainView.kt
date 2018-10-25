package com.n3k0.amplemindcleanarchitecture.presentation.boundary

import com.n3k0.amplemindcleanarchitecture.data.model.Country
import java.lang.Exception

interface MainView {
    fun showError(error: Exception)
    fun showList(itemCountries: List<ItemPresenter>)
    fun itemListClick(country: Country)
}