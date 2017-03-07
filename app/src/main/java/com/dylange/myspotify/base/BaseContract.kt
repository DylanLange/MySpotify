package com.dylange.myspotify.base

/**
 * Created by Dylan on 28-Feb-17.
 */
interface BaseContract {
    interface BaseView {
        fun showAlertDialog(title : String)
        fun fadeInCircleMenu()
        fun fadeOutCircleMenu()
        fun hideFab()
        fun showFab()
        fun showProgressDialog()
        fun hideProgressDialog()
    }
    interface BasePresenter {
        fun start()
    }
}