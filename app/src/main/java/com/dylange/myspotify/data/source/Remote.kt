package com.dylange.myspotify.data.source

import com.dylange.myspotify.data.models.*
import com.serjltt.moshi.adapters.Wrapped
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Dylan on 28-Feb-17.
 */

interface Remote {

    //-------------------------------------------------------------------
    // search
    //-------------------------------------------------------------------

    @GET("/v1/search?type=album")
    @Wrapped("albums")
    fun searchAlbums(@Header("Authorization") accessToken: String,
                     @Query("q") formattedQuery: String,
                     @Query("market") market: String?,
                     @Query("limit") limit: Int?,
                     @Query("offset") offset: Int?): Single<Paging<AlbumSimple>>

    @GET("/v1/search?type=artist")
    @Wrapped("artists")
    fun searchArtists(@Header("Authorization") accessToken: String,
                      @Query("q") formattedQuery: String,
                      @Query("market") market: String?,
                      @Query("limit") limit: Int?,
                      @Query("offset") offset: Int?): Single<Paging<Artist>>

    @GET("/v1/search?type=playlist")
    @Wrapped("playlists")
    fun searchPlaylists(@Header("Authorization") accessToken: String,
                        @Query("q") formattedQuery: String,
                        @Query("market") market: String?,
                        @Query("limit") limit: Int?,
                        @Query("offset") offset: Int?): Single<Paging<Playlist>>

    @GET("/v1/search?type=track")
    @Wrapped("tracks")
    fun searchTracks(@Header("Authorization") accessToken: String,
                     @Query("q") formattedQuery: String,
                     @Query("market") market: String?,
                     @Query("limit") limit: Int?,
                     @Query("offset") offset: Int?): Single<Paging<Track>>

    //-------------------------------------------------------------------
    // albums
    //-------------------------------------------------------------------

    @GET("/v1/albums/{id}")
    fun getAlbum(@Path("id") id: String): Single<Album>

    @GET("/v1/albums")
    @Wrapped("albums")
    fun getAlbums(@Query("ids") ids: String): Single<List<Album>>

    @GET("/v1/albums/{id}/tracks")
    fun getAlbumTracks(@Path("id") id: String): Single<Paging<TrackSimple>>

    //-------------------------------------------------------------------
    // artists
    //-------------------------------------------------------------------

    @GET("/v1/artists/{id}")
    fun getArtist(@Path("id") id: String): Single<Artist>

    @GET("/v1/artists")
    @Wrapped("artists")
    fun getArtists(@Query("ids") ids: String): Single<List<Artist>>

    @GET("v1/artists/{id}/albums")
    fun getArtistAlbums(@Path("id") id: String,
                        @Query("album_type") albumType: String?,
                        @Query("market") market: String?,
                        @Query("limit") limit: Int?,
                        @Query("offset") offset: Int?): Single<Paging<AlbumSimple>>

    @GET("/v1/artists/{id}/top-tracks")
    @Wrapped("tracks")
    fun getArtistTopTracks(@Path("id") id: String): Single<List<Track>>

    @GET("/v1/artists/{id}/related-artists")
    @Wrapped("artists")
    fun getArtistRelatedArtists(@Path("id") id: String): Single<List<Artist>>

    //-------------------------------------------------------------------
    // auth required
    //-------------------------------------------------------------------

    @GET("/v1/browse/new-releases")
    @Wrapped("albums")
    fun getNewReleases(@Header("Authorization") accessToken: String,
                       @Query("country") countryCode: String?,
                       @Query("limit") limit: Int?,
                       @Query("offset") offset: Int?): Single<Paging<AlbumSimple>>

    @GET("/v1/me/tracks")
    fun getMyTracks(@Header("Authorization") accessToken: String,
                    @Query("limit") limit: Int?,
                    @Query("offset") offset: Int?): Single<Paging<SavedTrack>>

    @PUT("/v1/me/albums")
    fun saveAlbums(@Header("Authorization") accessToken: String,
                   @Query("ids") ids: String): Single<Response<Void>>

    @GET("/v1/me/albums")
    fun getMyAlbums(@Header("Authorization") accessToken: String,
                    @Query("limit") limit: Int?,
                    @Query("offset") offset: Int?): Single<Paging<SavedAlbum>>

    @DELETE("/v1/me/albums")
    fun deleteMyAlbums(@Header("Authorization") accessToken: String,
                       @Query("ids") ids: String): Single<Response<Void>>

    @GET("/v1/me/albums/contains")
    fun checkMyAlbumsContainsAlbums(@Header("Authorization") accessToken: String,
                                    @Query("ids") ids: String): Single<Boolean>



    @GET("/v1/me/following?type=artist")
    @Wrapped("artists")
    fun getFollowedArtists(@Header("Authorization") accessToken: String,
                           @Query("limit") limit: Int?,
                           @Query("after") after: String?): Single<CursorPaging<Artist>>

    @PUT("/v1/me/following?type=artist")
    fun followArtists(@Header("Authorization") accessToken: String,
                      @Query("ids") commaSeparatedIDs: String): Single<Response<Void>>

    @PUT("/v1/me/following?type=user")
    fun followUsers(@Header("Authorization") accessToken: String,
                    @Query("ids") commaSeparatedIDs: String): Single<Response<Void>>

    @DELETE("/v1/me/following?type=artist")
    fun unfollowArtists(@Header("Authorization") accessToken: String,
                        @Query("ids") commaSeparatedIDs: String): Single<Response<Void>>

    @DELETE("/v1/me/following?type=user")
    fun unfollowUsers(@Header("Authorization") accessToken: String,
                      @Query("ids") commaSeparatedIDs: String): Single<Response<Void>>

    @GET("/v1/me/following/contains?type=artist")
    fun checkFollowsArtists(@Header("Authorization") accessToken: String,
                           @Query("ids") commaSeparatedIDs: String): Single<Boolean>

    @GET("/v1/me/following/contains?type=user")
    fun checkFollowsUsers(@Header("Authorization") accessToken: String,
                           @Query("ids") commaSeparatedIDs: String): Single<Boolean>

    @GET("/v1/me/top/artist")
    fun getMyTopArtists(@Header("Authorization") accessToken: String,
                        @Query("limit") limit: Int?,
                        @Query("offset") offset: Int?,
                        @Query("time_range") timeRange: String?): Single<Paging<Artist>>//time_range can be: long_term, medium_term, short_term -> several years, last 6 months, last month

    @GET("/v1/me/top/tracks")
    fun getMyTopTracks(@Header("Authorization") accessToken: String,
                        @Query("limit") limit: Int?,
                        @Query("offset") offset: Int?,
                        @Query("time_range") timeRange: String?): Single<Paging<Track>>//time_range can be: long_term, medium_term, short_term -> several years, last 6 months, last month

    @GET("/v1/me/player/recently-played")
    fun getRecentlyPlayed(@Header("Authorization") accessToken: String,
                          @Query("limit") limit: Int?): Single<CursorPaging<PlayHistory>>

}
