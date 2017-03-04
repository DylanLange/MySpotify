package com.dylange.myspotify.main.tracks

import com.dylange.myspotify.app.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by Dylan on 04-Mar-17.
 */
@Module
class TracksModule(val mFragment: TracksContract.View){
	@Provides @FragmentScope
	fun provideTracksView() : TracksContract.View = mFragment
}

@FragmentScope
@Subcomponent(modules = arrayOf(TracksModule::class))
interface TracksComponent{
	fun inject(fragment: TracksFragment)
}