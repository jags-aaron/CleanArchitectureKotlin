package com.n3k0.amplemindcleanarchitecture.platform.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.n3k0.amplemindcleanarchitecture.R
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.platform.adapter.Adapter
import com.n3k0.amplemindcleanarchitecture.platform.adapter.TypeFactoryImpl
import com.n3k0.amplemindcleanarchitecture.platform.navigation.Navigator
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityViewModel
import com.n3k0.amplemindcleanarchitecture.presentation.MainItemPresenter
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

open class MainActivity : AppCompatActivity(), MainView {

    private val disposables = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var render: MainRender

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var mAdapter: Adapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        viewManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()

        disposables.add(viewModel.states()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { state -> render.layout(this, state) })

        viewModel.processIntents(intents())
    }

    private fun intents(): Observable<MainIntents> {
        return Observable.merge(
            // default state
            Observable.just(MainIntents.InitialIntent),
            // Button click Intent
            RxView.clicks(btnGetData)
                .map { MainIntents.GetCountriesIntent(true) }
        )
    }

    override fun onStateChange(state: MainStates) {
        with(state) {
            showList(state.countryList)
            showError(error)
        }
    }

    fun itemListClick(country: Country) {
        Navigator.navigateToDetail(this, country)
    }

    private fun showError(error: Exception?) {
        if (error != null) {
            Toast.makeText(this, error?.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showList(countries: List<MainItemPresenter>) {
        mAdapter = Adapter(
            countries,
            viewModel,
            TypeFactoryImpl()
        )
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }
}
