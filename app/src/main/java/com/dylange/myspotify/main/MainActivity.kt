package com.dylange.myspotify.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.ViewGroup
import com.dylange.myspotify.BuildConfig
import com.dylange.myspotify.R
import com.dylange.myspotify.app.SpotifyAnalysisApplication
import com.dylange.myspotify.base.BaseActivity
import com.dylange.myspotify.main.albums.AlbumsFragment
import com.dylange.myspotify.main.artists.ArtistsFragment
import com.dylange.myspotify.main.playlists.PlaylistsFragment
import com.dylange.myspotify.main.tracks.TracksFragment
import com.imangazaliev.circlemenu.CircleMenu
import com.imangazaliev.circlemenu.CircleMenuButton
import com.mcxiaoke.koi.ext.onClick
import com.spotify.sdk.android.player.Config
import com.spotify.sdk.android.player.Spotify
import com.spotify.sdk.android.player.SpotifyPlayer
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

    lateinit var mPlayer: SpotifyPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        setSupportActionBar(toolbar)
        
        fab.onClick {
            mPresenter.fabClicked()
        }
        circle_menu.setStateUpdateListener(object: CircleMenu.OnStateUpdateListener {
            override fun onMenuCollapsed() {
                mPresenter.circleMenuClicked()
            }

            override fun onMenuExpanded() {

            }

        })
        circle_menu.setOnItemClickListener(object: CircleMenu.OnItemClickListener {
            override fun onItemClick(menuButton: CircleMenuButton?) {
                mPresenter.circleMenuItemClicked(menuButton)
            }
        })

        var playerConfig: Config = Config(this, mPrefs.getString(BaseActivity.AUTH_TOKEN_PREFS_KEY, ""), BuildConfig.SPOTIFY_CLIENT_ID)
        mPlayer = Spotify.getPlayer(playerConfig, this, object : SpotifyPlayer.InitializationObserver {
            override fun onInitialized(spotifyPlayer: SpotifyPlayer) {
//                mPlayer = spotifyPlayer
//                mPlayer.addConnectionStateCallback(this@LoginActivity)
//                mPlayer.addNotificationCallback(this@LoginActivity)
            }

            override fun onError(throwable: Throwable) {
                Log.e("MainActivity", "Could not initialize player: " + throwable.message)
            }
        })
    }

    override fun onDestroy() {
        if(mPlayer != null) mPlayer.destroy()
        super.onDestroy()
    }

    override fun setupActivityComponent() {
        SpotifyAnalysisApplication
                .get(this)
                .applicationComponent
                .plus(MainModule(this))
                .inject(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun showAlertDialog(title: String) {
        var builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(title)
                .setPositiveButton("Ok",
                        DialogInterface.OnClickListener {
                            dialog, i ->
                            dialog.dismiss()
                        })
        builder.create().show()
    }

    override fun setupTabs() {
        tab_layout.removeAllTabs()
        tab_layout.addTab(tab_layout.newTab().setIcon(resources.getDrawable(R.drawable.ic_track)).setTag("Tracks"))
        tab_layout.addTab(tab_layout.newTab().setIcon(resources.getDrawable(R.drawable.ic_album)).setTag("Albums"))
        tab_layout.addTab(tab_layout.newTab().setIcon(resources.getDrawable(R.drawable.ic_artist)).setTag("Artists"))
        tab_layout.addTab(tab_layout.newTab().setIcon(resources.getDrawable(R.drawable.ic_playlist)).setTag("Playlists"))
        toolbar.title = tab_layout.getTabAt(tab_layout.selectedTabPosition)!!.tag as CharSequence
        setupTabListeners()
    }

    private fun setupTabListeners() {
        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
                toolbar.title = tab.tag as CharSequence
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

        })

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                tab_layout.getTabAt(position)!!.select()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
        })
    }

    class ViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

        val NUM_PAGES : Int = 4
        lateinit var mTracksFragment: TracksFragment
        lateinit var mAlbumsFragment: AlbumsFragment
        lateinit var mArtistsFragment: ArtistsFragment
        lateinit var mPlaylistsFragment: PlaylistsFragment

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            when(position){
                0 -> {
                    mTracksFragment = super.instantiateItem(container, position) as TracksFragment
                    return mTracksFragment
                }
                1 -> {
                    mAlbumsFragment = super.instantiateItem(container, position) as AlbumsFragment
                    return mAlbumsFragment
                }
                2 -> {
                    mArtistsFragment = super.instantiateItem(container, position) as ArtistsFragment
                    return mArtistsFragment
                }
                3 -> {
                    mPlaylistsFragment = super.instantiateItem(container, position) as PlaylistsFragment
                    return mPlaylistsFragment
                }
                else -> return super.instantiateItem(container, position)
            }
        }

        override fun getItem(position: Int): Fragment? {
            if (position == 0) {
                return TracksFragment.newInstance()
            } else if (position == 1) {
                return AlbumsFragment.newInstance()
            } else if (position == 2) {
                return ArtistsFragment.newInstance()
            } else if (position == 3) {
                return PlaylistsFragment.newInstance()
            } else {
                return null
            }
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }

        override fun getPageTitle(position: Int): CharSequence {
            return super.getPageTitle(position)
        }

    }

}