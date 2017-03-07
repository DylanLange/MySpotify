package com.dylange.myspotify.data.models

import android.database.Cursor
import com.squareup.moshi.Json
import java.util.*

/**
 * Created by Dylan on 01-Mar-17.
 */

/**
 * track link
 */
data class TrackLink(
    @Json(name="external_urls") val externalUrls: ExternalUrl,//Known external URLs for this track.
                                val href: String,   //A link to the Web API endpoint providing full details of the track.
                                val id: String,     //The Spotify ID for the track.
                                val type: String,   //The object type: "track".
                                val uri: String     //The Spotify URI for the track.
)

/**
 * paging object
 *
 * The offset-based paging object is a container for a set of objects.
 * It contains a key called items (whose value is an array of the requested objects)
 * along with other keys like previous, next and limit that can be useful in future calls.
 */
data class Paging<T>(
        val href: String,           //A link to the Web API endpoint returning the full result of the request.
        val items: List<T>,         //The requested data.
        val limit: Int,             //The maximum number of items in the response (as set in the query or by default).
        val next: String,           //URL to the next page of items. (null if none)
        val offset: Int,            //The offset of the items returned (as set in the query or by default).
        val previous: String,       //URL to the previous page of items. (null if none)
        val total: Int              //The total number of items available to return.
)

/**
 * cursor-based paging object
 *
 * The cursor-based paging object is a container for a set of objects.
 * It contains a key called items (whose value is an array of the requested objects)
 * along with other keys like next and cursors that can be useful in future calls.
 */
data class CursorPaging<T>(
        val href: String,           //A link to the Web API endpoint returning the full result of the request.
        val items: List<T>,         //The requested data.
        val limit: Int,             //The maximum number of items in the response (as set in the query or by default).
        val next: String,           //URL to the next page of items. (null if none)
        val cursors: Cursor,        //The cursors used to find the next set of items.
        val total: Int              //The total number of items available to return.
)

data class Cursor(
        val after: String   //The cursor to use as key to find the next page of items.
)

/**
 * play history object
 */
data class PlayHistory(
                                val track: TrackSimple,
        @Json(name="played_at") val playedAt: String,
                                val context: SpotifyContext
)

/**
 * context object
 */
data class SpotifyContext(
                                    val type: String,
                                    val href: String,
        @Json(name="external_urls") val externalUrls: ExternalUrl,
                                    val uri: String
)

/**
 * copyright object
 */
data class Copyright(
        val text: String,   //The copyright text for this album.
        val type: String    //The type of copyright: C = the copyright, P = the sound recording (performance) copyright.
)

/**
 * image object
 */
data class SpotifyImage(
        val height: Int,    //The image height in pixels. If unknown: null or not returned.
        var url: String?,   //The source URL of the image. NOTE: may be null if it is album playlist image which is expiring in less than a day.
        val width: Int      //The image width in pixels. If unknown: null or not returned.
)

/**
 * followers object
 */
data class Followers(
        val href: String,   //A link to the Web API endpoint providing full details of the followers; null if not available. Please note that this will always be set to null, as the Web API does not support it at the moment.
        val total: Int      //Total number of followers
)

/**
 * external URL object
 */
open class ExternalUrl : HashMap<String, String>()

/**
 * external ID object
 */
open class ExternalId : HashMap<String, String>()

/**
 * user object (public)
 */
data class UserPublic(
        @Json(name="display_name")  val displayName: String,        //The name displayed on the user's profile. null if not available.
        @Json(name="external_urls") val externalUrls: ExternalUrl,  //Known public external URLs for this user.
                                    val followers: Followers,       //Information about the followers of this user.
                                    val href: String,               //A link to the Web API endpoint for this user.
                                    val id: String,                 //The Spotify user ID for this user.
                                    val images: List<SpotifyImage>, //The user's profile image.
                                    val type: String,               //The object type: "user"
                                    val uri: String                 //The Spotify URI for this user.
)

/**
 * playlist object (simplified)
 */
data class PlaylistSimple(
                                    val collaborative: Boolean,     //true if the owner allows other users to modify the playlist.
        @Json(name="external_urls") val externalUrls: ExternalUrl,  //Known external URLs for this playlist.
                                    val href: String,               //A link to the Web API endpoint providing full details of the playlist.
                                    val id: String,                 //The Spotify ID for the playlist.
                                    val images: List<SpotifyImage>, //Images for the playlist. The array may be empty or contain up to three images. The images are returned by size in descending order.
                                    val name: String,               //The name of the playlist.
                                    val owner: UserPublic,          //The user who owns the playlist
                                    var public: Boolean?,           //The playlist's public/private status: true the playlist is public, false the playlist is private, null the playlist status is not relevant.
        @Json(name="snapshot_id")   val snapshotId: String,         //The version identifier for the current playlist. Can be supplied in other requests to target a specific playlist version
                                    //val tracks: Track//wtf is this in API docs??
                                    val type: String,               //The object type: "playlist"
                                    val uri: String                 //The Spotify URI for the playlist.

)

/**
 * playlist object (full)
 */
data class Playlist(
                                    val collaborative: Boolean,     //true if the owner allows other users to modify the playlist.
                                    val description: String,        //The playlist description. Only returned for modified, verified playlists, otherwise null.
        @Json(name="external_urls") val externalUrls: ExternalUrl,  //Known external URLs for this playlist.
                                    val followers: Followers,       //Information about the followers of the playlist.
                                    val href: String,               //A link to the Web API endpoint providing full details of the playlist.
                                    val id: String,                 //The Spotify ID for the playlist.
                                    val images: List<SpotifyImage>, //Images for the playlist. The array may be empty or contain up to three images. The images are returned by size in descending order.
                                    val name: String,               //The name of the playlist.
                                    val owner: UserPublic,          //The user who owns the playlist
                                    var public: Boolean?,           //The playlist's public/private status: true the playlist is public, false the playlist is private, null the playlist status is not relevant.
        @Json(name="snapshot_id")   val snapshotId: String,         //The version identifier for the current playlist. Can be supplied in other requests to target a specific playlist version
                                    //val tracks: Track//wtf is this in API docs??
                                    val type: String,               //The object type: "playlist"
                                    val uri: String                 //The Spotify URI for the playlist.

)

/**
 * artist object (simplified)
 */
data class ArtistSimple(
        @Json(name="external_urls") val externalUrls: ExternalUrl, //Known external URLs for this artist. e.g. "spotify":"spotify-url"
                                    val href: String,   //A link to the Web API endpoint providing full details of the artist.
                                    val id: String,     //The Spotify ID for the artist.
                                    val name: String,   //The name of the artist
                                    val type: String,   //The object type: "artist"
                                    val uri: String     //The Spotify URI for the artist.
)

/**
 * artist object (full)
 */
data class Artist(
        @Json(name="external_urls") val externalUrls: ExternalUrl,  //Known external URLs for this artist. e.g. "spotify":"spotify-url"
                                    val followers: Followers,       //Information about the followers of the artist.
                                    val genres: List<String>,       //A list of the genres the artist is associated with. For example: "Prog Rock", "Post-Grunge". (If not yet classified, the array is empty.)
                                    val href: String,               //	A link to the Web API endpoint providing full details of the artist.
                                    val id: String,                 //The Spotify ID for the artist.
                                    val images: List<SpotifyImage>, //Images of the artist in various sizes, widest first.
                                    val name: String,               //The name of the artist
                                    val popularity: Int,            //The popularity of the artist. The value will be between 0 and 100, with 100 being the most popular. The artist's popularity is calculated from the popularity of all the artist's tracks.
                                    val type: String,               //The object type: "artist"
                                    val uri: String                 //The Spotify URI for the artist.
)

/**
 * album object(simplified)
 */
data class AlbumSimple(
        @Json(name="album_type")    val albumType: String,          //The type of the album: one of "album", "single", or "compilation".
                                    val artists: List<ArtistSimple>,//The artists of the album. Each artist object includes a link in href to more detailed information about the artist.
        @Json(name="available_markets") val availableMarkets: List<String>,//The markets in which the album is available: ISO 3166-1 alpha-2 country codes. Note that an album is considered available in a market when at least 1 of its tracks is available in that market.
        @Json(name="external_urls") val externalUrls: ExternalUrl,  //Known external URLs for this album.
                                    val href: String,               //A link to the Web API endpoint providing full details of the album.
                                    val id: String,                 //The Spotify ID for the album.
                                    val images: List<SpotifyImage>, //The cover art for the album in various sizes, widest first.
                                    val name: String,               //The name of the album. In case of an album takedown, the value may be an empty string.
                                    val type: String,               //The object type: "album"
                                    val uri: String                 //The Spotify URI for the album
)

/**
 * album object(full)
 */
data class Album(

        @Json(name="album_type")        val albumType: String,          //The type of the album: one of "album", "single", or "compilation".
                                        val artists: List<ArtistSimple>,//The artists of the album. Each artist object includes a link in href to more detailed information about the artist.
        @Json(name="available_markets") val availableMarkets: List<String>,//The markets in which the album is available: ISO 3166-1 alpha-2 country codes. Note that an album is considered available in a market when at least 1 of its tracks is available in that market.
                                        val copyrights: List<Copyright>,//The copyright statements of the album.
                                        val externalIds: List<ExternalId>,//Known external IDs for the album.
        @Json(name="external_urls")     val externalUrls: ExternalUrl,  //Known external URLs for this album.
                                        val genres: List<String>,       //A list of the genres used to classify the album. For example: "Prog Rock", "Post-Grunge". (If not yet classified, the array is empty.)
                                        val href: String,               //A link to the Web API endpoint providing full details of the album.
                                        val id: String,                 //The Spotify ID for the album.
                                        val images: List<SpotifyImage>, //The cover art for the album in various sizes, widest first.
                                        val label: String,              //The label for the album.
                                        val name: String,               //The name of the album. In case of an album takedown, the value may be an empty string.
                                        val popularity: Int,            //The popularity of the album. The value will be between 0 and 100, with 100 being the most popular.
        @Json(name="release_date")      val releaseDate: String,        //The date the album was first released, for example "1981-12-15". Depending on the precision, it might be shown as "1981" or "1981-12".
        @Json(name="release_date_precision") val preciseReleaseDate: String,//The precision with which release_date value is known: "year", "month", or "day".
                                        val tracks: Paging<TrackSimple>,//The tracks of the album.
                                        val type: String,               //The object type: "album"
                                        val uri: String                 //The Spotify URI for the album
)

/**
 * track object (simplified)
 */
data class TrackSimple(
                                    val artists: List<ArtistSimple>,//The artists who performed the track. Each artist object includes a link in href to more detailed information about the artist.
        @Json(name="available_markets") val availableMarkets: List<String>,//A list of the countries in which the track can be played, identified by their ISO 3166-1 alpha-2 code.
        @Json(name="disc_number")   val discNumber: Int,    //The disc number (usually 1 unless the album consists of more than one disc).
        @Json(name="duration_ms")   val duration: Int,      //The track length in milliseconds.
                                    val explicit: Boolean,  //Whether or not the track has explicit lyrics (true = yes it does; false = no it does not OR unknown).
        @Json(name="external_urls") val externalUrls: ExternalUrl,  //Known external URLs for this artist. e.g. "spotify":"spotify-url"
                                    val href: String,       //	A link to the Web API endpoint providing full details of the track.
                                    val id: String,         //The Spotify ID for the track.
        @Json(name="is_playable")   val isPlayable: Boolean,//Part of the response when Track Relinking is applied. If true, the track is playable in the given market. Otherwise false.
        @Json(name="linked_from")   val linkedFrom: TrackLink,//Part of the response when Track Relinking is applied and is only part of the response if the track linking, in fact, exists. The requested track has been replaced with a different track. The track in the linked_from object contains information about the originally requested track.
                                    val name: String,       //The name of the track.
        @Json(name="preview_url")   val previewUrl: String, //A URL to a 30 second preview (MP3 format) of the track.
        @Json(name="track_number")  val trackNumber: Int,   //	The number of the track. If an album has several discs, the track number is the number on the specified disc.
                                    val type: String,       //The object type: "track".
                                    val uri: String         //The Spotify URI for the track.
)

/**
 * track object (full)
 */
data class Track(
                                    val album: AlbumSimple,
                                    val artists: List<ArtistSimple>,//The artists who performed the track. Each artist object includes a link in href to more detailed information about the artist.
        @Json(name="available_markets") val availableMarkets: List<String>,//A list of the countries in which the track can be played, identified by their ISO 3166-1 alpha-2 code.
        @Json(name="disc_number")   val discNumber: Int,    //The disc number (usually 1 unless the album consists of more than one disc).
        @Json(name="duration_ms")   val duration: Int,      //The track length in milliseconds.
                                    val explicit: Boolean,  //Whether or not the track has explicit lyrics (true = yes it does; false = no it does not OR unknown).
                                    val externalIds: List<ExternalId>,//Known external IDs for the album.
        @Json(name="external_urls") val externalUrls: ExternalUrl,  //Known external URLs for this artist. e.g. "spotify":"spotify-url"
                                    val href: String,       //	A link to the Web API endpoint providing full details of the track.
                                    val id: String,         //The Spotify ID for the track.
        @Json(name="is_playable")   val isPlayable: Boolean,//Part of the response when Track Relinking is applied. If true, the track is playable in the given market. Otherwise false.
        @Json(name="linked_from")   val linkedFrom: TrackLink,//Part of the response when Track Relinking is applied and is only part of the response if the track linking, in fact, exists. The requested track has been replaced with a different track. The track in the linked_from object contains information about the originally requested track.
                                    val name: String,       //The name of the track.
                                    val popularity: Int,    //The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.
        @Json(name="preview_url")   val previewUrl: String, //A URL to a 30 second preview (MP3 format) of the track.
        @Json(name="track_number")  val trackNumber: Int,   //The number of the track. If an album has several discs, the track number is the number on the specified disc.
                                    val type: String,       //The object type: "track".
                                    val uri: String         //The Spotify URI for the track.
)

/**
 * saved track object
 */
data class SavedTrack(
        @Json(name="added_at")  val addedAt: String,    //The date and time the track was saved.
                                val track: Track        //Information about the track.
)

/**
 * saved album  object
 */
data class SavedAlbum(
        @Json(name="added_at")  val addedAt: String,    //The date and time the album was saved.
                                val track: Track        //Information about the album.
)

/**
 * audio features object
 */
data class AudioFeatures(
                                    val acousticness: Float,    //A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track is acoustic.
        @Json(name="analysis_url")  val analysisUrl: String,    //An HTTP URL to access the full audio analysis of this track. An access token is required to access this data.
                                    val danceability: Float,    //Danceability describes how suitable a track is for dancing based on a combination of musical elements including tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
        @Json(name="duration_ms")   val duration: Int,          //The duration of the track in milliseconds.
                                    val energy: Float,          //Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity. Typically, energetic tracks feel fast, loud, and noisy. For example, death metal has high energy, while a Bach prelude scores low on the scale. Perceptual features contributing to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general entropy.
                                    val id: String,             //The Spotify ID for the track.
                                    val instrumentalness: Float,//Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context. Rap or spoken word tracks are clearly "vocal". The closer the instrumentalness value is to 1.0, the greater likelihood the track contains no vocal content. Values above 0.5 are intended to represent instrumental tracks, but confidence is higher as the value approaches 1.0.
                                    val key: Int,               //The key the track is in. Integers map to pitches using standard Pitch Class notation. E.g. 0 = C, 1 = C♯/D♭, 2 = D, and so on.
                                    val liveness: Float,        //Detects the presence of an audience in the recording. Higher liveness values represent an increased probability that the track was performed live. A value above 0.8 provides strong likelihood that the track is live.
                                    val loudness: Float,        //The overall loudness of a track in decibels (dB). Loudness values are averaged across the entire track and are useful for comparing relative loudness of tracks. Loudness is the quality of a sound that is the primary psychological correlate of physical strength (amplitude). Values typical range between -60 and 0 db.
                                    val mode: Int,              //Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is derived. Major is represented by 1 and minor is 0.
                                    val speechiness: Float,     //Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the attribute value. Values above 0.66 describe tracks that are probably made entirely of spoken words. Values between 0.33 and 0.66 describe tracks that may contain both music and speech, either in sections or layered, including such cases as rap music. Values below 0.33 most likely represent music and other non-speech-like tracks.
                                    val tempo: Float,           //The overall estimated tempo of a track in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
        @Json(name="time_signature")val timeSignature: Int,     //An estimated overall time signature of a track. The time signature (meter) is a notational convention to specify how many beats are in each bar (or measure).
        @Json(name="track_href")    val trackHref: String,      //A link to the Web API endpoint providing full details of the track.
                                    val type: String,           //The object type: "audio_features"
                                    val uri: String,            //The Spotify URI for the track.
                                    val valence: Float          //A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric), while tracks with low valence sound more negative (e.g. sad, depressed, angry).
) {
    fun getKeyAsString(): String {
        when(key){
            0 -> return "C"
            1 -> return "C#"
            2 -> return "D"
            3 -> return "D#"
            4 -> return "E"
            5 -> return "F"
            6 -> return "F#"
            7 -> return "G"
            8 -> return "G#"
            9 -> return "A"
            10 -> return "A#"
            11 -> return "B"
        }
        return "Invalid key."
    }

    fun getModeAsString(): String {
        when(mode){
            0 -> return "minor"
            1 -> return "major"
        }
        return "Invalid mode."
    }
}