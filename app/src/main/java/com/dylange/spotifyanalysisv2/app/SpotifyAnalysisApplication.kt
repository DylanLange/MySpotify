package com.dylange.spotifyanalysisv2.app

import android.app.Application
import android.content.Context

/**
 * Created by Dylan on 28-Feb-17.
 */
class SpotifyAnalysisApplication : Application() {
    companion object {
        @JvmStatic fun get(context: Context): SpotifyAnalysisApplication {
            return context.applicationContext as SpotifyAnalysisApplication
        }
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}