package com.n3k0.amplemindcleanarchitecture.presentation

import com.n3k0.amplemindcleanarchitecture.data.common.fold
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.MainView
import com.n3k0.amplemindcleanarchitecture.presentation.common.BasePresenter

open class MainActivityPresenter(
    private val view: MainView,
    private val useCaseFactory: UseCaseFactory
) : BasePresenter() {

    override fun onViewReady() {

    }

    fun getMockData() {
        useCaseFactory.getCountries().execute { result ->
            result.fold(
                { onError(it) },
                { countries -> changeToItemPresenter(countries) }
            )
        }
    }

    fun onError(failure: Exception) {
        view.showError(failure.message!!)
    }

    fun onSuccess(countryItems: List<MainItemPresenter>) {
        view.showList(countryItems)
    }

    fun onItemClicked(country: Country) {
        view.itemListClick(country)
    }

    private fun changeToItemPresenter(countries: List<Country>) {
        useCaseFactory.convertCountriesToItemPresenter(countries, useCaseFactory).execute {
            onSuccess(it)
        }
    }

}