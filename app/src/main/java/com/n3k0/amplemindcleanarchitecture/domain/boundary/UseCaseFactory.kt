package com.n3k0.amplemindcleanarchitecture.domain.boundary

import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.domain.CountriesToItemPresenterUseCase
import com.n3k0.amplemindcleanarchitecture.domain.GetCountriesUseCase

open interface UseCaseFactory {
    fun getCountries(): GetCountriesUseCase
    fun convertCountriesToItemPresenter(countries: List<Country>, useCaseFactory: UseCaseFactory): CountriesToItemPresenterUseCase
}