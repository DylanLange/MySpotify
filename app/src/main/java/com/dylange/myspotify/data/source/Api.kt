package com.dylange.myspotify.data.source

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

    @Inject
    constructor(remote: Remote){
        mRemote = remote
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

    override fun getNewReleases(accessToken: String, countryCode: String?, limit: Int?, offset: Int?): Single<Paging<AlbumSimple>> {
        return createDefaultSubscription(mRemote.getNewReleases(accessToken, countryCode, limit, offset))
    }

    override fun saveAlbums(accessToken: String, ids: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.saveAlbums(accessToken, ids))
    }

    override fun getMyAlbums(accessToken: String): Single<Paging<SavedAlbum>> {
        return createDefaultSubscription(mRemote.getMyAlbums(accessToken))
    }

    override fun deleteMyAlbums(accessToken: String, ids: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.deleteMyAlbums(accessToken, ids))
    }

    override fun checkMyAlbumsContainsAlbums(accessToken: String, ids: String): Single<Boolean> {
        return createDefaultSubscription(mRemote.checkMyAlbumsContainsAlbums(accessToken, ids))
    }

    override fun getFollowedArtists(accessToken: String, limit: Int?, after: String?): Single<CursorPaging<Artist>> {
        return createDefaultSubscription(mRemote.getFollowedArtists(accessToken, limit, after))
    }

    override fun followArtists(accessToken: String, commaSeparatedIDs: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.followArtists(accessToken, commaSeparatedIDs))
    }

    override fun followUsers(accessToken: String, commaSeparatedIDs: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.followUsers(accessToken, commaSeparatedIDs))
    }

    override fun unfollowArtists(accessToken: String, commaSeparatedIDs: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.unfollowArtists(accessToken, commaSeparatedIDs))
    }

    override fun unfollowUsers(accessToken: String, commaSeparatedIDs: String): Single<Response<Void>> {
        return createDefaultSubscription(mRemote.unfollowUsers(accessToken, commaSeparatedIDs))
    }

    override fun checkFollowsArtists(accessToken: String, commaSeparatedIDs: String): Single<Boolean> {
        return createDefaultSubscription(mRemote.checkFollowsArtists(accessToken, commaSeparatedIDs))
    }

    override fun checkFollowsUsers(accessToken: String, commaSeparatedIDs: String): Single<Boolean> {
        return createDefaultSubscription(mRemote.checkFollowsUsers(accessToken, commaSeparatedIDs))
    }

    override fun getMyTopArtists(accessToken: String, limit: Int?, offset: Int?, timeRange: String?): Single<Paging<Artist>> {
        return createDefaultSubscription(mRemote.getMyTopArtists(accessToken, limit, offset, timeRange))
    }

    override fun getMyTopTracks(accessToken: String, limit: Int?, offset: Int?, timeRange: String?): Single<Paging<Track>> {
        return createDefaultSubscription(mRemote.getMyTopTracks(accessToken, limit, offset, timeRange))
    }

    override fun getRecentlyPlayed(accessToken: String, limit: Int?): Single<CursorPaging<PlayHistory>> {
        return createDefaultSubscription(mRemote.getRecentlyPlayed(accessToken, limit))
    }

    fun <T> createDefaultSubscription(thing : Single<T>) : Single<T> = thing
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}