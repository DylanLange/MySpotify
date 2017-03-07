package com.dylange.myspotify.app

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.dylange.myspotify.BuildConfig
import com.dylange.myspotify.data.source.Api
import com.dylange.myspotify.data.source.Remote
import com.dylange.myspotify.login.LoginComponent
import com.dylange.myspotify.login.LoginModule
import com.dylange.myspotify.main.MainComponent
import com.dylange.myspotify.main.MainModule
import com.dylange.myspotify.main.albums.AlbumsComponent
import com.dylange.myspotify.main.albums.AlbumsModule
import com.dylange.myspotify.main.artists.ArtistsComponent
import com.dylange.myspotify.main.artists.ArtistsModule
import com.dylange.myspotify.main.playlists.PlaylistsComponent
import com.dylange.myspotify.main.playlists.PlaylistsModule
import com.dylange.myspotify.main.tracks.TracksComponent
import com.dylange.myspotify.main.tracks.TracksModule
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.serjltt.moshi.adapters.WrappedJsonAdapter
import com.squareup.moshi.Moshi
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Dylan on 28-Feb-17.
 */
@Module
class ApplicationModule(val mApplication: SpotifyAnalysisApplication){
    @Provides
    @AppScope
    fun provideApplication(): SpotifyAnalysisApplication = mApplication

    @Provides
    @AppScope
    fun provideSharedPrefs(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(mApplication)

    @Provides
    @AppScope
    internal fun provideDataSource(remote: Remote, prefs: SharedPreferences): Api {
        return Api(remote, prefs)
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
        val cache = Cache(mApplication.cacheDir, (1024 * 1024 * 10).toLong())

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
        return retrofit.create<Remote>(Remote::class.java)
    }
}

@AppScope
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(app: SpotifyAnalysisApplication)

    fun plus(mainModule: MainModule): MainComponent
    fun plus(loginModule: LoginModule): LoginComponent
    fun plus(tracksModule: TracksModule): TracksComponent
    fun plus(playlistsModule: PlaylistsModule): PlaylistsComponent
    fun plus(artistsModule: ArtistsModule): ArtistsComponent
    fun plus(albumsModule: AlbumsModule): AlbumsComponent

}