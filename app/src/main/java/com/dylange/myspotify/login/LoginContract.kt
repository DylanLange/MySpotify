package com.dylange.myspotify.main

import com.dylange.myspotify.base.BaseContract

/**
 * Created by Dylan on 28-Feb-17.
 */
interface LoginContract {
    interface View : BaseContract.BaseView {
        fun setupOnClickListeners()
        fun goToSpotifyLogin()
        fun goToMain()
    }
    interface Presenter: BaseContract.BasePresenter {
        fun loginBtnClicked()
        fun loginSuccessful()
    }
}