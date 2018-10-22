package com.n3k0.amplemindcleanarchitecture.platform.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.n3k0.amplemindcleanarchitecture.R
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.platform.adapter.Adapter
import com.n3k0.amplemindcleanarchitecture.platform.adapter.TypeFactoryImpl
import com.n3k0.amplemindcleanarchitecture.platform.common.BaseActivity
import com.n3k0.amplemindcleanarchitecture.platform.navigation.Navigator
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityPresenter
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.ItemPresenter
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.MainView
import com.n3k0.amplemindcleanarchitecture.presentation.common.BasePresenter
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : BaseActivity(), MainView {

//    @Inject
//    lateinit var presenter: MainActivityPresenter

    var presenter: MainActivityPresenter? = null

    private lateinit var mAdapter: Adapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = getCompositionRoot()?.getPresenter(this)

        viewManager = LinearLayoutManager(this)

        btnGetData.setOnClickListener {
            presenter?.getMockData()
        }
    }

    override fun showList(itemCountries: List<ItemPresenter>) {
        mAdapter = Adapter(
            itemCountries,
            presenter as BasePresenter,
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

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onViewReady()
    }
}
