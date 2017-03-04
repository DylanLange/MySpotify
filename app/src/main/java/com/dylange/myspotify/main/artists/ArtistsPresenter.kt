package com.dylange.myspotify.main.artists

import com.dylange.myspotify.data.source.Api
import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class ArtistsPresenter : ArtistsContract.Presenter {

    var mView : ArtistsContract.View
    var mRepo: Api

    @Inject constructor(view : ArtistsContract.View, repo: Api){
        mView = view
        mRepo = repo
    }

    override fun start(){

    }

}