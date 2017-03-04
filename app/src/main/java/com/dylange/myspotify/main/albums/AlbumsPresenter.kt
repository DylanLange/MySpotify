package com.dylange.myspotify.main.albums

import com.dylange.myspotify.data.source.Api
import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class AlbumsPresenter: AlbumsContract.Presenter {

    var mView : AlbumsContract.View
    var mRepo: Api

    @Inject constructor(view : AlbumsContract.View, repo: Api){
        mView = view
        mRepo = repo
    }

    override fun start(){

    }

}