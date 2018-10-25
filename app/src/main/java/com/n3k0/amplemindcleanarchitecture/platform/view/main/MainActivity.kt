package com.n3k0.amplemindcleanarchitecture.platform.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.n3k0.amplemindcleanarchitecture.R
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.platform.adapter.Adapter
import com.n3k0.amplemindcleanarchitecture.platform.adapter.TypeFactoryImpl
import com.n3k0.amplemindcleanarchitecture.platform.navigation.Navigator
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityViewModel
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.ItemPresenter
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.MainView
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import javax.inject.Inject

open class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mAdapter: Adapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)

        viewManager = LinearLayoutManager(this)

        btnGetData.setOnClickListener {
            viewModel.getMockData()
        }
    }

    /* --------------------- OBSERVERS --------------------------*/

    override fun onStart() {
        super.onStart()
        compositeDisposable.add(viewModel.errorSubject.subscribe(this::showError))
        compositeDisposable.add(viewModel.successSubject.subscribe(this::showList))
        compositeDisposable.add(viewModel.itemClickSubject.subscribe(this::itemListClick))
    }

    override fun onStop() {
        compositeDisposable.clear()
        viewModel.onStop()
        super.onStop()
    }

    /* --------------------- RX TRIGGERS --------------------------*/

    override fun showList(itemCountries: List<ItemPresenter>) {
        mAdapter = Adapter(
            itemCountries,
            viewModel,
            TypeFactoryImpl()
        )
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }
    }

    override fun itemListClick(country: Country) {
        Navigator.navigateToDetail(this, country)
    }

    override fun showError(error: Exception) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }
}
