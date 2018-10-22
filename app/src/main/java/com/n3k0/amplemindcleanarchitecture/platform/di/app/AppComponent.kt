package com.n3k0.amplemindcleanarchitecture.platform.di.app

import android.app.Application
import com.n3k0.amplemindcleanarchitecture.platform.MyApp
import com.n3k0.amplemindcleanarchitecture.platform.di.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent {

    fun inject(app: MyApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}