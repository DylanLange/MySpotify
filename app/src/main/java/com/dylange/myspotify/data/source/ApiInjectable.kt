package com.dylange.myspotify.data.source

import com.dylange.myspotify.BuildConfig
import com.dylange.myspotify.app.ActivityScope
import com.dylange.myspotify.app.AppScope
import com.dylange.myspotify.app.SpotifyAnalysisApplication
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.serjltt.moshi.adapters.WrappedJsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Dylan on 02-Mar-17.
 */
@Module
class ApiModule {

    private val app: SpotifyAnalysisApplication

    constructor(app: SpotifyAnalysisApplication) {
        this.app = app
    }

    @Provides
    @AppScope
    internal fun provideDataSource(remote: Remote): Api {
        return Api(remote)
    }

    @Provides
    @AppScope
    internal fun provideRetrofit(client: OkHttpClient, moshi: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(moshi)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    @AppScope
    internal fun provideClient(): OkHttpClient {
        val cache = Cache(app.cacheDir, (1024 * 1024 * 10).toLong())

        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor { chain ->

                    val rb = chain.request().newBuilder()

                    var ignoreCache = false

                    //If request has the header, get it's value and remove it.
                    if (chain.request().headers().get("ignore-cache") != null) {
                        ignoreCache = chain.request().headers().get("ignore-cache").toLowerCase() == "true"
                        rb.removeHeader("ignore-cache")
                    }

                    //If it's true, force a network request
                    if (ignoreCache) {
                        rb.cacheControl(CacheControl.FORCE_NETWORK)
                    }

                    chain.proceed(rb.build())
                }
                .build()
    }

    @Provides
    @AppScope
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(WrappedJsonAdapter.FACTORY)
                .build()
    }

    @Provides
    @AppScope
    internal fun provideMoshiFactory(moshi: Moshi): MoshiConverterFactory {

        return MoshiConverterFactory.create(moshi).asLenient()
    }

    @Provides
    @AppScope
    internal fun provideService(retrofit: Retrofit): Remote {
        return retrofit.create<Remote>(Remote::class.java!!)
    }

}

@ActivityScope
@Subcomponent(modules = arrayOf(ApiModule::class))
interface ApiComponent {
    fun inject(application: SpotifyAnalysisApplication)
}