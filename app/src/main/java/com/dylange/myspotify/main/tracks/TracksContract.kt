package com.dylange.myspotify.main.tracks

import com.dylange.myspotify.base.BaseContract
import com.dylange.myspotify.data.models.Paging
import com.dylange.myspotify.data.models.SavedTrack

/**
 * Created by Dylan on 04-Mar-17.
 */
interface TracksContract {
	interface View: BaseContract.BaseView {
		fun setAdapter(tracksPage: Paging<SavedTrack>)
	}
	interface Presenter: BaseContract.BasePresenter {

	}
}