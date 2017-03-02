package com.dylange.spotifyanalysisv2.main

import android.content.SharedPreferences
import android.os.Bundle
import com.dylange.spotifyanalysisv2.R
import com.dylange.spotifyanalysisv2.app.SpotifyAnalysisApplication
import com.dylange.spotifyanalysisv2.base.BaseActivity
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by Dylan on 28-Feb-17.
 */
class MainActivity: BaseActivity(), MainContract.View {

    @Inject
    lateinit var mPresenter: MainPresenter

    @Inject
    lateinit var mPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test_btn.onClick { mPresenter.btnClicked() }

    }

    override fun setupActivityComponent() {
        SpotifyAnalysisApplication
                .get(this)
                .applicationComponent
                .plus(MainModule(this))
                .inject(this)
    }

}