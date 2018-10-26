package com.n3k0.amplemindcleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import com.n3k0.amplemindcleanarchitecture.data.common.Either
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainActions
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainIntents
import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainStates
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.lang.Exception
import javax.inject.Inject

open class MainActivityViewModel @Inject constructor(
    private val useCaseFactory: UseCaseFactory
) : ViewModel() {

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

    fun processIntents(intents: Observable<MainIntents>) {
        intents.subscribe(intentsSubject)
    }

    fun states(): Observable<MainStates> = statesObservable

    /**
     * Add new Intents on runtime
     * */
    fun publish(intent: MainIntents) {
        intentsSubject.onNext(intent)
    }

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
            is MainActions.SuccessAction -> {
                previousState.copy(progress = false, success = renderAction.countries)
            }
            is MainActions.InitialAction -> {
                previousState.copy(progress = true, success = emptyList())
            }
            is MainActions.ErrorAction -> {
                previousState.copy(progress = false, error = renderAction.failure, success = emptyList())
            }
            is MainActions.ShowDetailAction -> {
                previousState.copy(progress = false, success = emptyList(), country = renderAction.country)
            }
        }
    }

    private fun dispatcher(intent: MainIntents): Observable<MainActions> {
        return when (intent) {
            MainIntents.InitialIntent -> {
                Observable.just(MainActions.InitialAction(true))
            }
            is MainIntents.GetCountriesIntent -> {
                Observable.just(MainActions.SuccessAction(emptyList()))
            }
            is MainIntents.ShowDetailIntent -> {
                Observable.just(MainActions.ShowDetailAction(intent.country))
            }
        }
    }

    fun retornarPendejada(){
        val example: Unit = useCaseFactory.getCountries().execute {
            "Test"
        }

        val res: MainActions = runTransformation {
            "caseA"
        }
    }

    fun runTransformation(f: (String) -> String): MainActions {
        return MainActions.InitialAction(true)
    }

    private fun changeToItemPresenter(countries: List<Country>, func: (List<MainItemPresenter>) -> List<MainItemPresenter> ) {
        useCaseFactory.convertCountriesToItemPresenter(countries, useCaseFactory).execute {
            func(it)
        }
    }

}