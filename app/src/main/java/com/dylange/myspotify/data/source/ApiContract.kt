package com.dylange.myspotify.data.source

import com.dylange.myspotify.data.models.*
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by Dylan on 02-Mar-17.
 */
interface ApiContract {

    //-------------------------------------------------------------------
    // search
    //-------------------------------------------------------------------

    fun searchAlbums(accessToken: String,
                     formattedQuery: String,
                     market: String?,
                     limit: Int?,
                     offset: Int?): Single<Paging<AlbumSimple>>

    fun searchArtists(accessToken: String,
                      formattedQuery: String,
                      market: String?,
                      limit: Int?,
                      offset: Int?): Single<Paging<Artist>>

    fun searchPlaylists(accessToken: String,
                        formattedQuery: String,
                        market: String?,
                        limit: Int?,
                        offset: Int?): Single<Paging<Playlist>>

    fun searchTracks(accessToken: String,
                     formattedQuery: String,
                     market: String?,
                     limit: Int?,
                     offset: Int?): Single<Paging<Track>>

    //-------------------------------------------------------------------
    // albums
    //-------------------------------------------------------------------

    fun getAlbum(id: String): Single<Album>

    fun getAlbums(ids: String): Single<List<Album>>//comma separated IDs

    fun getAlbumTracks(id: String): Single<Paging<TrackSimple>>

    //-------------------------------------------------------------------
    // artists
    //-------------------------------------------------------------------

    fun getArtist(id: String): Single<Artist>

    fun getArtists(ids: String): Single<List<Artist>>

    fun getArtistAlbums(id: String,
                        albumType: String?,
                        market: String?,
                        limit: Int?,
                        offset: Int?): Single<Paging<AlbumSimple>>

    fun getArtistTopTracks(id: String): Single<List<Track>>

    fun getArtistRelatedArtists(id: String): Single<List<Artist>>

    //-------------------------------------------------------------------
    // auth required
    //-------------------------------------------------------------------

    fun getNewReleases(countryCode: String?,
                       limit: Int?,
                       offset: Int?): Single<Paging<AlbumSimple>>

    fun getMyTracks(limit: Int?,
                    offset: Int?): Single<Paging<SavedTrack>>

    fun saveAlbums(ids: String): Single<Response<Void>>

    fun getMyAlbums(limit: Int?,
                    offset: Int?): Single<Paging<SavedAlbum>>

    fun deleteMyAlbums(ids: String): Single<Response<Void>>

    fun checkMyAlbumsContainsAlbums(ids: String): Single<Boolean>

    fun getFollowedArtists(limit: Int?,
                           after: String?): Single<CursorPaging<Artist>>

    fun followArtists(commaSeparatedIDs: String): Single<Response<Void>>

    fun followUsers(commaSeparatedIDs: String): Single<Response<Void>>

    fun unfollowArtists(commaSeparatedIDs: String): Single<Response<Void>>

    fun unfollowUsers(commaSeparatedIDs: String): Single<Response<Void>>

    fun checkFollowsArtists(commaSeparatedIDs: String): Single<Boolean>

    fun checkFollowsUsers(commaSeparatedIDs: String): Single<Boolean>

    fun getMyTopArtists(limit: Int?,
                        offset: Int?,
                        timeRange: String?): Single<Paging<Artist>>

    fun getMyTopTracks(limit: Int?,
                        offset: Int?,
                        timeRange: String?): Single<Paging<Track>>

    fun getRecentlyPlayed(limit: Int?): Single<CursorPaging<PlayHistory>>

}