package com.dylange.myspotify.main.albums

import com.dylange.myspotify.app.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by Dylan on 04-Mar-17.
 */
@Module
class AlbumsModule(val mFragment: AlbumsContract.View){
    @Provides @FragmentScope
    fun provideAlbumsView() : AlbumsContract.View = mFragment
}

@FragmentScope
@Subcomponent(modules = arrayOf(AlbumsModule::class))
interface AlbumsComponent{
    fun inject(fragment: AlbumsFragment)
}