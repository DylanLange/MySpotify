package com.dylange.myspotify.main.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylange.myspotify.R
import com.dylange.myspotify.app.SpotifyAnalysisApplication
import com.dylange.myspotify.base.BaseFragment
import com.imangazaliev.circlemenu.CircleMenu
import com.imangazaliev.circlemenu.CircleMenuButton
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by dylanlange on 2/02/17.
 */

class TracksFragment : BaseFragment(), TracksContract.View {

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


}