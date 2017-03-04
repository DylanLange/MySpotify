package com.dylange.myspotify.main.albums

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
class AlbumsFragment : BaseFragment(), AlbumsContract.View {


    companion object {
        @JvmStatic fun newInstance() = AlbumsFragment()
    }

    @Inject lateinit var mPresenter: AlbumsPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_albums, container, false)

    override fun setupFragmentComponent() {
        SpotifyAnalysisApplication
                .get(this.context)
                .applicationComponent
                .plus(AlbumsModule(this))
                .inject(this)
    }

}