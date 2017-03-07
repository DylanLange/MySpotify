package com.dylange.myspotify.main.tracks

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dylange.myspotify.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.track_view.view.*
import java.util.concurrent.TimeUnit

/**
 * Created by Dylan on 07-Mar-17.
 */
class TrackView: LinearLayout {

    lateinit var mContext: Context

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(context)
    }

    fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.track_view, this, true)

        mContext = context
    }

    fun setTrackName(trackName: String) {
        tv_track_name.text = trackName
    }

    fun setAlbumName(albumName: String){
        tv_album_name.text = albumName
    }

    fun setDuration(duration: Int){
        var mins: Long = TimeUnit.MILLISECONDS.toMinutes(duration.toLong())
        var seconds: Long = TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) - (mins * 60)
        tv_track_duration.text = String.format("%d:%02d", mins.toInt(), seconds.toInt())
    }

    fun loadAlbumImage(albumImageLink: String) {
        Picasso.with(mContext)
                .load(albumImageLink)
                .into(iv_album_image)
    }

}