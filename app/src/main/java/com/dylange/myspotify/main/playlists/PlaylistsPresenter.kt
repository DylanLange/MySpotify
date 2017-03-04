package com.dylange.myspotify.main.playlists

import com.dylange.myspotify.data.source.Api
import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class PlaylistsPresenter : PlaylistsContract.Presenter {

    var mView : PlaylistsContract.View
    var mRepo: Api

    @Inject constructor(view : PlaylistsContract.View, repo: Api){
        mView = view
        mRepo = repo
    }

    override fun start(){

    }

}