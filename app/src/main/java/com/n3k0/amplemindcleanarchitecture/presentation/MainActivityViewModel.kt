package com.n3k0.amplemindcleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import com.n3k0.amplemindcleanarchitecture.data.common.Either
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainActions
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainIntents
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainStates
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

open class MainActivityViewModel @Inject constructor(
    private val useCaseFactory: UseCaseFactory
) : ViewModel() {


    /*--------------------- SUBJECTS ----------------------*/

    private val intentsSubject: PublishSubject<MainIntents> = PublishSubject.create()
    private val statesObservable = intentsSubject
        .compose { intents ->
            intents.publish {
                filterIntents(intents)
            }
        }
        .flatMap {
            dispatcher(it)
        }
        .scan(MainStates()) { previousState: MainStates, renderAction: MainActions ->
            reducer(previousState, renderAction)
        }
        .map {
            it
        }
        .distinctUntilChanged()
        .replay(1)
        .autoConnect(0)

    /*--------------------- VIEW INTERACTIONS ----------------------*/

    fun processIntents(intents: Observable<MainIntents>) {
        intents.subscribe(intentsSubject)
    }

    fun states(): Observable<MainStates> = statesObservable

    /**
     * Add new Intents on runtime
     * */
    fun publishIntent(intent: MainIntents) {
        intentsSubject.onNext(intent)
    }

    /*------------------------- MVI FLOW -------------------------*/

    private fun filterIntents(intents: Observable<MainIntents>): Observable<MainIntents> {
        return Observable.merge(
            intents.ofType(MainIntents.InitialIntent.javaClass).take(1),
            intents.filter {
                !MainIntents.InitialIntent.javaClass.isInstance(it)
            }
        )
    }

    private fun reducer(previousState: MainStates, renderAction: MainActions): MainStates {
        return when (renderAction) {
            is MainActions.InitialAction -> {
                previousState.copy(progress = true, countryList = emptyList())
            }
            is MainActions.GettingData -> {
                previousState.copy(progress = true, countryList = emptyList())
            }
            is MainActions.ShowDetail -> {
                previousState.copy(progress = false, countryList = emptyList(), countrySelected = renderAction.country)
            }
            is MainActions.ErrorAction -> {
                previousState.copy(progress = false, countryList = emptyList(), error = renderAction.failure)
            }
            is MainActions.SuccessAction -> {
                previousState.copy(progress = false, countryList = renderAction.countries)
            }
        }
    }

    private fun dispatcher(intent: MainIntents): Observable<MainActions> {
        return when (intent) {
            MainIntents.InitialIntent -> {
                Observable.just(MainActions.InitialAction(true))
            }
            is MainIntents.GetCountriesIntent -> {
                getCountries() // <-- getting data from api
                Observable.just(MainActions.GettingData)
            }
            is MainIntents.ShowDetailIntent -> {
                Observable.just(MainActions.ShowDetail(intent.country))
            }
            is MainIntents.ShowErrorIntent -> {
                Observable.just(MainActions.ErrorAction(intent.failure))
            }
            is MainIntents.ShowSuccessIntent -> {
                Observable.just(MainActions.SuccessAction(intent.countries))
            }
        }
    }

    /*------------------------- USE CASE INTERACTIONS -------------------------*/

    fun getCountries() {
        useCaseFactory.getCountries().execute {
            when (it) {
                is Either.Left -> {
                    publishIntent(MainIntents.ShowErrorIntent(it.value))
                }
                is Either.Right -> {
                    val items: List<MainItemPresenter> = it.value.map {
                        MainItemPresenter(it, useCaseFactory)
                    }
                    publishIntent(MainIntents.ShowSuccessIntent(items))
                }
            }
        }
    }
}