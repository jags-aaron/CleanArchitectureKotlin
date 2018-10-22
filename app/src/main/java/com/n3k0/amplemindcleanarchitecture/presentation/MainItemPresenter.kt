package com.n3k0.amplemindcleanarchitecture.presentation

import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.TypeFactory
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.ItemPresenter

class MainItemPresenter(
    val country: Country,
    private val useCaseFactory: UseCaseFactory
): ItemPresenter {

    override fun type(typeFactory: TypeFactory): Int {
        return typeFactory.type(this)
    }

    fun getCountryName(): String? {
        return country.name
    }
}