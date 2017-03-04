package com.dylange.myspotify.main.playlists

import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class PlaylistsPresenter : PlaylistsContract.Presenter {

    var mView : PlaylistsContract.View

    @Inject constructor(view : PlaylistsContract.View){
        mView = view
    }

    override fun start(){

    }

}