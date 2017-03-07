package com.dylange.myspotify.main.tracks

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylange.myspotify.R
import com.dylange.myspotify.app.SpotifyAnalysisApplication
import com.dylange.myspotify.base.BaseFragment
import com.dylange.myspotify.data.models.Paging
import com.dylange.myspotify.data.models.SavedTrack
import com.dylange.myspotify.data.models.Track
import com.dylange.myspotify.main.MainContract
import kotlinx.android.synthetic.main.fragment_tracks.*
import javax.inject.Inject

/**
 * Created by dylanlange on 2/02/17.
 */

class TracksFragment : BaseFragment(), TracksContract.View{

    companion object {
        @JvmStatic fun newInstance() = TracksFragment()
    }

    @Inject lateinit var mPresenter: TracksPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_tracks, container, false)

    override fun setupFragmentComponent() {
        SpotifyAnalysisApplication
                .get(this.context)
                .applicationComponent
                .plus(TracksModule(this))
                .inject(this)
    }

    override fun setAdapter(tracksPage: Paging<SavedTrack>) {
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = TracksRecyclerAdapter(context, tracksPage, mPresenter)
        recycler_view.adapter.notifyDataSetChanged()
    }

    override fun playTrack(track: Track) {
        (activity as MainContract.View).playTrack(track)
    }

}