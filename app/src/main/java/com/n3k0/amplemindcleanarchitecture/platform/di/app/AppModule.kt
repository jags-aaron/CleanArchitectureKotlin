package com.n3k0.amplemindcleanarchitecture.platform.di.app

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache {
        val cacheFile = File(application.applicationContext
                .cacheDir.absolutePath, "HttpCache")
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(cacheFile, cacheSize.toLong())
        return cache
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okBuilder = OkHttpClient().newBuilder()
        okBuilder.cache(cache)
        okBuilder.readTimeout(20, TimeUnit.SECONDS)
        okBuilder.connectTimeout(10, TimeUnit.SECONDS)
        okBuilder.writeTimeout(20, TimeUnit.SECONDS)
        okBuilder.addInterceptor(interceptor)
        return okBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://restcountries.eu/")
                .client(okHttpClient)
                .build()
    }

}
