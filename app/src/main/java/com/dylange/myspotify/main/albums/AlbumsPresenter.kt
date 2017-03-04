package com.dylange.myspotify.main.albums

import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class AlbumsPresenter: AlbumsContract.Presenter {

    var mView : AlbumsContract.View

    @Inject constructor(view : AlbumsContract.View){
        mView = view
    }

    override fun start(){

    }

}