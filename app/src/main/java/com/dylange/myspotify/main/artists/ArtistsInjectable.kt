package com.dylange.myspotify.main.artists

import com.dylange.myspotify.app.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by Dylan on 04-Mar-17.
 */
@Module
class ArtistsModule(val mFragment: ArtistsContract.View){
    @Provides @FragmentScope
    fun provideArtistsView() : ArtistsContract.View = mFragment
}

@FragmentScope
@Subcomponent(modules = arrayOf(ArtistsModule::class))
interface ArtistsComponent{
    fun inject(fragment: ArtistsFragment)
}