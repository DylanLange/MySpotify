package com.dylange.myspotify.main.tracks

import com.dylange.myspotify.base.BaseContract
import com.dylange.myspotify.data.models.Paging
import com.dylange.myspotify.data.models.SavedTrack
import com.dylange.myspotify.data.models.Track

/**
 * Created by Dylan on 04-Mar-17.
 */
interface TracksContract {
	interface View: BaseContract.BaseView {
		fun setAdapter(tracksPage: Paging<SavedTrack>)
		fun playTrack(track: Track)
	}
	interface Presenter: BaseContract.BasePresenter {

	}
}