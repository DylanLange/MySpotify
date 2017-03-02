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
        return mRemote.searchAlbums(accessToken, formattedQuery, market, limit, offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchArtists(accessToken: String, formattedQuery: String, market: String?, limit: Int?, offset: Int?): Single<Paging<Artist>> {
        return mRemote.searchArtists(accessToken, formattedQuery, market, limit, offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchPlaylists(accessToken: String, formattedQuery: String, market: String?, limit: Int?, offset: Int?): Single<Paging<Playlist>> {
        return mRemote.searchPlaylists(accessToken, formattedQuery, market, limit, offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchTracks(accessToken: String, formattedQuery: String, market: String?, limit: Int?, offset: Int?): Single<Paging<Track>> {
        return mRemote.searchTracks(accessToken, formattedQuery, market, limit, offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAlbum(id: String): Single<Album> {
        return mRemote.getAlbum(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAlbums(ids: String): Single<List<Album>> {
        return mRemote.getAlbums(ids).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAlbumTracks(id: String): Single<Paging<TrackSimple>> {
        return mRemote.getAlbumTracks(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArtist(id: String): Single<Artist> {
        return mRemote.getArtist(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArtists(ids: String): Single<List<Artist>>{
        return mRemote.getArtists(ids).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArtistAlbums(id: String, albumType: String?, market: String?, limit: Int?, offset: Int?): Single<Paging<AlbumSimple>> {
        return mRemote.getArtistAlbums(id, albumType, market, limit, offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArtistTopTracks(id: String): Single<List<Track>> {
        return mRemote.getArtistTopTracks(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArtistRelatedArtists(id: String): Single<List<Artist>> {
        return mRemote.getArtistRelatedArtists(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNewReleases(accessToken: String, countryCode: String?, limit: Int?, offset: Int?): Single<Paging<AlbumSimple>> {
        return mRemote.getNewReleases(accessToken, countryCode, limit, offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveAlbums(accessToken: String, ids: String): Single<Response<Void>> {
        return mRemote.saveAlbums(accessToken, ids).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMyAlbums(accessToken: String): Single<Paging<SavedAlbum>> {
        return mRemote.getMyAlbums(accessToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteMyAlbums(accessToken: String, ids: String): Single<Response<Void>> {
        return mRemote.deleteMyAlbums(accessToken, ids).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun checkMyAlbumsContainsAlbums(accessToken: String, ids: String): Single<Boolean> {
        return mRemote.checkMyAlbumsContainsAlbums(accessToken, ids).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFollowedArtists(accessToken: String, limit: Int?, after: String?): Single<CursorPaging<Artist>> {
        return mRemote.getFollowedArtists(accessToken, limit, after).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun followArtists(accessToken: String, commaSeparatedIDs: String): Single<Response<Void>> {
        return mRemote.followArtists(accessToken, commaSeparatedIDs).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun followUsers(accessToken: String, commaSeparatedIDs: String): Single<Response<Void>> {
        return mRemote.followUsers(accessToken, commaSeparatedIDs).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun unfollowArtists(accessToken: String, commaSeparatedIDs: String): Single<Response<Void>> {
        return mRemote.unfollowArtists(accessToken, commaSeparatedIDs).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun unfollowUsers(accessToken: String, commaSeparatedIDs: String): Single<Response<Void>> {
        return mRemote.unfollowUsers(accessToken, commaSeparatedIDs).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun checkFollowsArtists(accessToken: String, commaSeparatedIDs: String): Single<Boolean> {
        return mRemote.checkFollowsArtists(accessToken, commaSeparatedIDs).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun checkFollowsUsers(accessToken: String, commaSeparatedIDs: String): Single<Boolean> {
        return mRemote.checkFollowsUsers(accessToken, commaSeparatedIDs).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMyTopArtists(accessToken: String, limit: Int?, offset: Int?, timeRange: String?): Single<Paging<Artist>> {
        return mRemote.getMyTopArtists(accessToken, limit, offset, timeRange).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMyTopTracks(accessToken: String, limit: Int?, offset: Int?, timeRange: String?): Single<Paging<Track>> {
        return mRemote.getMyTopTracks(accessToken, limit, offset, timeRange).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}