package com.dylange.myspotify.main.artists

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
class ArtistsFragment : BaseFragment(), ArtistsContract.View {

    companion object {
        @JvmStatic fun newInstance() = ArtistsFragment()
    }

    @Inject lateinit var mPresenter: ArtistsPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_artists, container, false)

    override fun setupFragmentComponent() {
        SpotifyAnalysisApplication
                .get(this.context)
                .applicationComponent
                .plus(ArtistsModule(this))
                .inject(this)
    }

}