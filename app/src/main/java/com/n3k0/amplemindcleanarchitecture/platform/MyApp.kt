package com.n3k0.amplemindcleanarchitecture.platform

import android.app.Activity
import android.app.Application
import com.n3k0.amplemindcleanarchitecture.platform.di.app.DaggerAppComponent
import com.n3k0.amplemindcleanarchitecture.platform.dinodagger.app.CompositionRoot
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    // Dependency Injection (Without dagger)
    private var mCompositionRoot: CompositionRoot? = null

    fun getCompositionRoot(): CompositionRoot? = mCompositionRoot

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        mCompositionRoot = CompositionRoot(this)
    }

}