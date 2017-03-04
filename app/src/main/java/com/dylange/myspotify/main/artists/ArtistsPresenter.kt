package com.dylange.myspotify.main.artists

import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class ArtistsPresenter : ArtistsContract.Presenter {

    var mView : ArtistsContract.View

    @Inject constructor(view : ArtistsContract.View){
        mView = view
    }

    override fun start(){

    }

}