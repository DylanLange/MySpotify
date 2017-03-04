package com.dylange.myspotify.base

/**
 * Created by Dylan on 28-Feb-17.
 */
interface BaseContract {
    interface BaseView {
        fun showAlertDialog(title : String)
    }
    interface BasePresenter {
        fun start()
    }
}