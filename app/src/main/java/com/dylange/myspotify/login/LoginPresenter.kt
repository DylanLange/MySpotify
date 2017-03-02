package com.dylange.myspotify.login

import com.dylange.myspotify.main.LoginContract
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