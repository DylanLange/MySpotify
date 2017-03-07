package com.dylange.myspotify.main

import com.dylange.myspotify.base.BaseContract
import com.dylange.myspotify.data.models.Track
import com.imangazaliev.circlemenu.CircleMenuButton

/**
 * Created by Dylan on 28-Feb-17.
 */
interface MainContract {
    interface View : BaseContract.BaseView {
        fun setupTabs()
        fun playTrack(track: Track)
    }
    interface Presenter: BaseContract.BasePresenter {
        fun btnClicked()
        fun fabClicked()
        fun circleMenuClicked()
        fun circleMenuItemClicked(menuButton: CircleMenuButton?)
    }
}