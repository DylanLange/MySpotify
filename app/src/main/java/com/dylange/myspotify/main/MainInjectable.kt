package com.dylange.myspotify.main

import com.dylange.myspotify.app.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by Dylan on 28-Feb-17.
 */

@Module
class MainModule(val mActivity: MainContract.View){
    @Provides @ActivityScope
    fun provideMainView(): MainContract.View = mActivity
}

@ActivityScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}