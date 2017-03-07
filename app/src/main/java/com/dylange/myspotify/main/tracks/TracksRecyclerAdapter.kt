package com.dylange.myspotify.main.tracks

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.dylange.myspotify.data.models.Paging
import com.dylange.myspotify.data.models.SavedTrack

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
        trackView.setTrackName(mTracks.items[position].track.name)
    }

    override fun getItemCount(): Int =  mTracks.items.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

}