package com.n3k0.amplemindcleanarchitecture.platform.view.detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.n3k0.amplemindcleanarchitecture.R
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.presentation.DetailActivityPresenter
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.DetailView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity(), DetailView {

    @Inject
    lateinit var presenter: DetailActivityPresenter

    companion object {
        val COUNTRY_IMAGE = "COUNTRY_IMAGE"
        val COUNTRY_NAME = "COUNTRY_NAME"
        fun launch(activity: Activity, country: Country) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(COUNTRY_NAME, country.name)
            intent.putExtra(COUNTRY_IMAGE, country.flag)

            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter.onViewReady()
    }

    override fun showFlag(source: String) {
        val uri = Uri.parse(source)
        GlideToVectorYou.justLoadImage(this, uri, ivCountryDetail)
    }

    override fun changeText(text: String) {
        tvExtraData.text = text
    }
}
