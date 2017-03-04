package com.dylange.myspotify.main.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylange.myspotify.R
import com.dylange.myspotify.app.SpotifyAnalysisApplication
import com.dylange.myspotify.base.BaseFragment
import javax.inject.Inject

/**
 * Created by Dylan on 04-Mar-17.
 */
class PlaylistsFragment : BaseFragment(), PlaylistsContract.View  {

    companion object {
        @JvmStatic fun newInstance() = PlaylistsFragment()
    }

    @Inject lateinit var mPresenter: PlaylistsPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_playlists, container, false)

    override fun setupFragmentComponent() {
        SpotifyAnalysisApplication
                .get(this.context)
                .applicationComponent
                .plus(PlaylistsModule(this))
                .inject(this)
    }

}