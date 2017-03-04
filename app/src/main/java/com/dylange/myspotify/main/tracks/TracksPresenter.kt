package com.dylange.myspotify.main.tracks

import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class TracksPresenter : TracksContract.Presenter{

	var mView : TracksContract.View

	@Inject constructor(view : TracksContract.View){
		mView = view
	}

	override fun start(){

	}
}