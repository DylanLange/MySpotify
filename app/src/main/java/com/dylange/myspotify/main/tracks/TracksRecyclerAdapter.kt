package com.dylange.myspotify.main.tracks

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.dylange.myspotify.data.models.Paging
import com.dylange.myspotify.data.models.SavedTrack
import com.dylange.myspotify.data.models.Track

/**
 * Created by Dylan on 07-Mar-17.
 */
class TracksRecyclerAdapter(var mContext: Context, var mTracks: Paging<SavedTrack>): RecyclerView.Adapter<TracksRecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TracksRecyclerAdapter.ViewHolder {
        var view: TrackView = TrackView(mContext)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TracksRecyclerAdapter.ViewHolder?, position: Int) {
        var trackView: TrackView = holder!!.itemView as TrackView

        var track: Track = mTracks.items[position].track
        trackView.setTrackName(track.name)
        trackView.setAlbumName(track.album.name)
        trackView.setDuration(track.duration)
        trackView.loadAlbumImage(track.album.images[0].url!!)
    }

    override fun getItemCount(): Int =  mTracks.items.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

}