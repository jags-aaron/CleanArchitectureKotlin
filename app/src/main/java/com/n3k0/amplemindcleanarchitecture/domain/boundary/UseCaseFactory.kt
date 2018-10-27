package com.n3k0.amplemindcleanarchitecture.domain.boundary

import com.n3k0.amplemindcleanarchitecture.domain.GetCountriesUseCase

open interface UseCaseFactory {
    fun getCountries(): GetCountriesUseCase
}