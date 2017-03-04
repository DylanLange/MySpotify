package com.dylange.myspotify.main

import android.content.SharedPreferences
import android.util.Log
import com.dylange.myspotify.base.BaseActivity.Companion.AUTH_TOKEN_PREFS_KEY
import com.dylange.myspotify.data.models.SavedAlbum
import com.dylange.myspotify.data.source.Api
import javax.inject.Inject

/**
 * Created by Dylan on 28-Feb-17.
 */
class MainPresenter: MainContract.Presenter {

    var mView: MainContract.View
    var mRepo: Api
    var mPrefs: SharedPreferences

    @Inject
    constructor(view: MainContract.View, repo: Api, prefs: SharedPreferences){
        mView = view
        mRepo = repo
        mPrefs = prefs
    }

    override fun start() {
        mView.setupTabs()
    }

    override fun btnClicked() {
        val accessToken: String = mPrefs.getString(AUTH_TOKEN_PREFS_KEY, "");
        mRepo.searchArtists(accessToken, "afi", null, null, null).subscribe {
            response ->
            Log.d("DYLAN", response.toString())
        }
    }

    private fun getMyAlbums(accessToken: String){
        mRepo.getMyAlbums(accessToken).subscribe {
            response ->
            for (album: SavedAlbum in response.items) {
                Log.d("DYLAN", album.toString())
            }
        }
    }

    private fun getAlbum(accessToken: String){
        mRepo.getAlbum("1eK4nhdVZTpIzibRw7qWiw").subscribe {
            response ->
            Log.d("DYLAN", response.toString())
        }
    }

    private fun getAlbums(accessToken: String){
        mRepo.getAlbums("1eK4nhdVZTpIzibRw7qWiw,73h2unQGoSEL75TlZVl7Pb").subscribe {
            response ->
            Log.d("DYLAN", response.toString())
        }
    }

}