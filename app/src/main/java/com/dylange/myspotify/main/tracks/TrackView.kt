package com.dylange.myspotify.main.tracks

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dylange.myspotify.R
import kotlinx.android.synthetic.main.track_view.view.*

/**
 * Created by Dylan on 07-Mar-17.
 */
class TrackView: LinearLayout {

    constructor(context: Context): super(context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.track_view, this, true)
    }

    fun setTrackName(trackName: String) {
        track_name.text = trackName
    }

}