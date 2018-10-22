package com.n3k0.amplemindcleanarchitecture.platform.navigation

import android.app.Activity
import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.platform.view.detail.DetailActivity

class Navigator {

    companion object {
        fun navigateToDetail(activity: Activity?, country: Country) {
            if (activity != null) {
                DetailActivity.launch(activity, country)
            }
        }
    }
}