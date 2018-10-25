package com.n3k0.amplemindcleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import com.n3k0.amplemindcleanarchitecture.data.common.fold
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.UnicastSubject
import javax.inject.Inject

open class MainActivityViewModel @Inject constructor(
    private val useCaseFactory: UseCaseFactory
) : ViewModel() {

    val errorSubject: BehaviorSubject<Exception> = BehaviorSubject.create()
    val successSubject: BehaviorSubject<List<MainItemPresenter>> = BehaviorSubject.create()
    var itemClickSubject: UnicastSubject<Country> = getUnicasteSubject()

    /* --------------------- SUBJECTS UPDATE --------------------------*/

    private fun onError(failure: Exception) {
        errorSubject.onNext(failure)
    }

    private fun onSuccess(countryItems: List<MainItemPresenter>) {
        successSubject.onNext(countryItems)
    }

    private fun getUnicasteSubject(): UnicastSubject<Country> {
        return UnicastSubject.create()
    }

    /* --------------------- USED FOR VIEW --------------------------*/

    fun getMockData() {
        useCaseFactory.getCountries().execute { result ->
            result.fold(
                { onError(it) },
                { countries -> changeToItemPresenter(countries) }
            )
        }
    }

    fun onItemClicked(country: Country) {
        itemClickSubject.onNext(country)
    }

    private fun changeToItemPresenter(countries: List<Country>) {
        useCaseFactory.convertCountriesToItemPresenter(countries, useCaseFactory).execute {
            onSuccess(it)
        }
    }

    /*
    * this is because UnicastSubject can be subscribed once
    * and after released all the emissions to the Observer it cleans its cache automatically
    * */
    fun onStop(){
        itemClickSubject = getUnicasteSubject()
    }

}