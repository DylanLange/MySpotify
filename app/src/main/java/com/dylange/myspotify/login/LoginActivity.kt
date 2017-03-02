package com.dylange.myspotify.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.dylange.myspotify.BuildConfig
import com.dylange.myspotify.R
import com.dylange.myspotify.app.SpotifyAnalysisApplication
import com.dylange.myspotify.base.BaseActivity
import com.dylange.myspotify.main.LoginContract
import com.dylange.myspotify.main.MainActivity
import com.mcxiaoke.koi.ext.onClick
import com.mcxiaoke.koi.ext.startActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.*
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject



/**
 * Created by Dylan on 28-Feb-17.
 */
class LoginActivity: BaseActivity(), LoginContract.View, Player.NotificationCallback, ConnectionStateCallback {

    val REQUEST_CODE: Int = 1337

    @Inject
    lateinit var mPresenter: LoginPresenter

    @Inject
    lateinit var mPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun setupActivityComponent() {
        SpotifyAnalysisApplication
                .get(this)
                .applicationComponent
                .plus(LoginModule(this))
                .inject(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            var response: AuthenticationResponse = AuthenticationClient.getResponse(resultCode, data)
            if(response.type == AuthenticationResponse.Type.TOKEN) {
                saveTokenToSharedPrefs("Bearer " + response.accessToken)
                var playerConfig: Config = Config(this, response.accessToken, BuildConfig.SPOTIFY_CLIENT_ID)

//                mPlayer = Spotify.getPlayer(playerConfig, this, object : SpotifyPlayer.InitializationObserver {
//                    override fun onInitialized(spotifyPlayer: SpotifyPlayer) {
////                        mPlayer = spotifyPlayer
////                        mPlayer.addConnectionStateCallback(this@LoginActivity)
////                        mPlayer.addNotificationCallback(this@LoginActivity)
//                    }
//
//                    override fun onError(throwable: Throwable) {
//                        Log.e("MainActivity", "Could not initialize player: " + throwable.message)
//                    }
//                })

                mPresenter.loginSuccessful()
            }
        }
    }

    override fun onPlaybackError(error: Error?) { }

    override fun onPlaybackEvent(playerEvent: PlayerEvent?) { }

    override fun onConnectionMessage(connectionMessage: String?) { }

    override fun onLoggedOut() { }

    override fun onLoggedIn() {
        mPresenter.loginSuccessful()
    }

    override fun onLoginFailed(error: Error?) { }

    override fun onTemporaryError() { }

    override fun setupOnClickListeners() {
        btn_login.onClick {
            mPresenter.loginBtnClicked()
        }
    }

    override fun goToSpotifyLogin() {
        var builder: AuthenticationRequest.Builder = AuthenticationRequest.Builder(
                BuildConfig.SPOTIFY_CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                BuildConfig.SPOTIFY_REDIRECT_URI)
                .setScopes(arrayOf("playlist-read-collaborative", "playlist-read-private", "user-library-read", "streaming"))

        var request: AuthenticationRequest = builder.build()
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun goToMain() {
        startActivity<MainActivity>()
        finish()
    }

    private fun saveTokenToSharedPrefs(token: String){
        var editor: SharedPreferences.Editor = mPreferences.edit()
        editor.putString(AUTH_TOKEN_PREFS_KEY, token)
        editor.apply()
    }

}