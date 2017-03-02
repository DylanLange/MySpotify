package com.dylange.spotifyanalysisv2.login

import com.dylange.spotifyanalysisv2.main.LoginContract
import javax.inject.Inject

/**
 * Created by Dylan on 28-Feb-17.
 */
class LoginPresenter: LoginContract.Presenter {

    var mView: LoginContract.View

    @Inject
    constructor(view: LoginContract.View){
        mView = view
    }

    override fun start() {
        mView.setupOnClickListeners()
    }

    override fun loginSuccessful() {
        mView.goToMain()
    }

    override fun loginBtnClicked() {
        mView.goToSpotifyLogin()
    }
}