package com.n3k0.amplemindcleanarchitecture.platform.common

import androidx.appcompat.app.AppCompatActivity
import com.n3k0.amplemindcleanarchitecture.platform.MyApp
import com.n3k0.amplemindcleanarchitecture.platform.dinodagger.feature.MainActivityComposition

open class BaseActivity : AppCompatActivity() {

    private var mMainActivityComposition: MainActivityComposition? = null

    fun getCompositionRoot(): MainActivityComposition? {
        if (mMainActivityComposition == null) {
            mMainActivityComposition = MainActivityComposition((application as MyApp).getCompositionRoot()!!)
        }

        return mMainActivityComposition
    }
}