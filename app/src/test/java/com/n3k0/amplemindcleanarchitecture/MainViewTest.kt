package com.n3k0.amplemindcleanarchitecture

import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainActivity
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewTest {

    @Mock
    private lateinit var view: MainActivity

    @Mock
    private lateinit var useCaseFactory: UseCaseFactory

    private lateinit var presenter: MainActivityPresenter

    @Before
    fun setup() {
        presenter = MainActivityPresenter(view, useCaseFactory)
    }

    @Test
    fun onItemListClick() {
        // Given
        presenter.onItemClicked(mockCountry())
        // Then
        verify(view).itemListClick(mockCountry())
    }

    @Test
    fun onCountriesRetrieved() {
        // Given
        val countries = getCountries()
    }

}