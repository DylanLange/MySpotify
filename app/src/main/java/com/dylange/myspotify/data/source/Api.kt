package com.dylange.myspotify.data.source

import android.content.SharedPreferences
import com.dylange.myspotify.base.BaseActivity
import com.dylange.myspotify.data.models.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Dylan on 02-Mar-17.
 */
class Api : ApiContract {

    val mRemote: Remote
    val mPrefs: SharedPreferences

    @Inject
    constructor(remote: Remote, prefs: SharedPreferences){
        mRemote = remote
        mPrefs = prefs
    }

    override fun searchAlbums(accessToken: String, formattedQuery: String, market: String?, limit: Int?, offset: Int?): Single<Paging<AlbumSimple>> {
        return createDefaultSubscription(mRemote.searchAlbums(accessToken, formattedQuery, market, limit, offset))
    }

    override fun searchArtists(accessToken: String, formattedQuery: String, market: String?, limit: Int?, offset: Int?): Single<Paging<Artist>> {
        return createDefaultSubscription(mRemote.searchArtists(accessToken, formattedQuery, market, limit, offset))
    }

    override fun searchPlaylists(accessToken: String, formattedQuery: String, market: String?, limit: Int?, offset: Int?): Single<Paging<Playlist>> {
        return createDefaultSubscription(mRemote.searchPlaylists(accessToken, formattedQuery, market, limit, offset))
    }

    override fun searchTracks(accessToken: String, formattedQuery: String, market: String?, limit: Int?, offset: Int?): Single<Paging<Track>> {
        return createDefaultSubscription(mRemote.searchTracks(accessToken, formattedQuery, market, limit, offset))
    }

    override fun getAlbum(id: String): Single<Album> {
        return createDefaultSubscription(mRemote.getAlbum(id))
    }

    override fun getAlbums(ids: String): Single<List<Album>> {
        return createDefaultSubscription(mRemote.getAlbums(ids))
    }

    override fun getAlbumTracks(id: String): Single<Paging<TrackSimple>> {
        return createDefaultSubscription(mRemote.getAlbumTracks(id))
    }

    override fun getArtist(id: String): Single<Artist> {
        return createDefaultSubscription(mRemote.getArtist(id))
    }

    override fun getArtists(ids: String): Single<List<Artist>>{
        return createDefaultSubscription(mRemote.getArtists(ids))
    }

    override fun getArtistAlbums(id: String, albumType: String?, market: String?, limit: Int?, offset: Int?): Single<Paging<AlbumSimple>> {
        return createDefaultSubscription(mRemote.getArtistAlbums(id, albumType, market, limit, offset))
    }

    override fun getArtistTopTracks(id: String): Single<List<Track>> {
        return createDefaultSubscription(mRemote.getArtistTopTracks(id))
    }

    override fun getArtistRelatedArtists(id: String): Single<List<Artist>> {
        return createDefaultSubscription(mRemote.getArtistRelatedArtists(id))
    }

    override fun getNewReleases(countryCode: String?, limit: Int?, offset: Int?): Single<Paging<AlbumSimple>> {
        return createDefaultSubscription(mRemote.getNewReleases("Bearer " + getAccessToken(), countryCode, limit, offset))
    }

    override fun getMyTracks(limit: Int?, offset: Int?): Single<Paging<SavedTrack>> {
        return createDefaultSubscription(mRemote.getMyTracks("Bearer " + getAccessToken(), limit, offset))
    }

    override fun saveAlbums(ids: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.saveAlbums("Bearer " + getAccessToken(), ids))
    }

    override fun getMyAlbums(limit: Int?, offset: Int?): Single<Paging<SavedAlbum>> {
        return createDefaultSubscription(mRemote.getMyAlbums("Bearer " + getAccessToken(), limit, offset))
    }

    override fun deleteMyAlbums(ids: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.deleteMyAlbums("Bearer " + getAccessToken(), ids))
    }

    override fun checkMyAlbumsContainsAlbums(ids: String): Single<Boolean> {
        return createDefaultSubscription(mRemote.checkMyAlbumsContainsAlbums("Bearer " + getAccessToken(), ids))
    }

    override fun getFollowedArtists(limit: Int?, after: String?): Single<CursorPaging<Artist>> {
        return createDefaultSubscription(mRemote.getFollowedArtists("Bearer " + getAccessToken(), limit, after))
    }

    override fun followArtists(commaSeparatedIDs: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.followArtists("Bearer " + getAccessToken(), commaSeparatedIDs))
    }

    override fun followUsers(commaSeparatedIDs: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.followUsers("Bearer " + getAccessToken(), commaSeparatedIDs))
    }

    override fun unfollowArtists(commaSeparatedIDs: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.unfollowArtists("Bearer " + getAccessToken(), commaSeparatedIDs))
    }

    override fun unfollowUsers(commaSeparatedIDs: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.unfollowUsers("Bearer " + getAccessToken(), commaSeparatedIDs))
    }

    override fun checkFollowsArtists(commaSeparatedIDs: String): Single<Boolean> {
        return createDefaultSubscription(mRemote.checkFollowsArtists("Bearer " + getAccessToken(), commaSeparatedIDs))
    }

    override fun checkFollowsUsers(commaSeparatedIDs: String): Single<Boolean> {
        return createDefaultSubscription(mRemote.checkFollowsUsers("Bearer " + getAccessToken(), commaSeparatedIDs))
    }

    override fun getMyTopArtists(limit: Int?, offset: Int?, timeRange: String?): Single<Paging<Artist>> {
        return createDefaultSubscription(mRemote.getMyTopArtists("Bearer " + getAccessToken(), limit, offset, timeRange))
    }

    override fun getMyTopTracks(limit: Int?, offset: Int?, timeRange: String?): Single<Paging<Track>> {
        return createDefaultSubscription(mRemote.getMyTopTracks("Bearer " + getAccessToken(), limit, offset, timeRange))
    }

    override fun getRecentlyPlayed(limit: Int?): Single<CursorPaging<PlayHistory>> {
        return createDefaultSubscription(mRemote.getRecentlyPlayed("Bearer " + getAccessToken(), limit))
    }

    fun <T> createDefaultSubscription(thing : Single<T>) : Single<T> = thing
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getAccessToken(): String = mPrefs.getString(BaseActivity.AUTH_TOKEN_PREFS_KEY, "")

}