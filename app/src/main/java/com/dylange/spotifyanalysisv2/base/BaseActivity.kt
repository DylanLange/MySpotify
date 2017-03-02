package com.dylange.spotifyanalysisv2.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Dylan on 28-Feb-17.
 */
abstract class BaseActivity: AppCompatActivity(), BaseContract.BaseView {

    companion object{
        @JvmStatic val AUTH_TOKEN_PREFS_KEY = "_AUTH_TOKEN_PREFS_KEY"
    }

    //lateinit var mPlayer: SpotifyPlayer

    abstract fun setupActivityComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityComponent()
    }

    override fun onDestroy() {
        //if(mPlayer != null) mPlayer.destroy()
        super.onDestroy()
    }

}