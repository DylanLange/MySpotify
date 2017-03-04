package com.dylange.myspotify.main.playlists

import com.dylange.myspotify.app.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by Dylan on 04-Mar-17.
 */
@Module
class PlaylistsModule(val mFragment: PlaylistsContract.View){
    @Provides @FragmentScope
    fun providePlaylistsView() : PlaylistsContract.View = mFragment
}

@FragmentScope
@Subcomponent(modules = arrayOf(PlaylistsModule::class))
interface PlaylistsComponent{
    fun inject(fragment: PlaylistsFragment)
}