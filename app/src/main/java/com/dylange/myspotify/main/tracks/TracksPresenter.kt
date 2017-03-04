package com.dylange.myspotify.main.tracks

import com.dylange.myspotify.data.source.Api
import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class TracksPresenter : TracksContract.Presenter{

	var mView : TracksContract.View
	var mRepo: Api

	@Inject constructor(view : TracksContract.View, repo: Api){
		mView = view
		mRepo = repo
	}

	override fun start(){

	}
}