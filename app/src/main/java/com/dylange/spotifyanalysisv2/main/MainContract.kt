package com.dylange.spotifyanalysisv2.main

import com.dylange.spotifyanalysisv2.base.BaseContract

/**
 * Created by Dylan on 28-Feb-17.
 */
interface MainContract {
    interface View : BaseContract.BaseView {

    }
    interface Presenter: BaseContract.BasePresenter {
        fun btnClicked()
    }
}