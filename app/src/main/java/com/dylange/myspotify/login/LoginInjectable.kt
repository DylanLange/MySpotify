package com.dylange.myspotify.login

import com.dylange.myspotify.app.ActivityScope
import com.dylange.myspotify.main.LoginContract
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by Dylan on 28-Feb-17.
 */
@Module
class LoginModule(val mActivity: LoginContract.View){
    @Provides @ActivityScope
    fun provideLoginView(): LoginContract.View = mActivity
}

@ActivityScope
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent {
    fun inject(activity: LoginActivity)
}