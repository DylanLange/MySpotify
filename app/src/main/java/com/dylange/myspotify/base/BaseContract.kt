package com.dylange.myspotify.base

import com.imangazaliev.circlemenu.CircleMenuButton

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
    }
    interface BasePresenter {
        fun start()
    }
}