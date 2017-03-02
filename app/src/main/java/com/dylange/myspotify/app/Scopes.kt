package com.dylange.myspotify.app

import javax.inject.Scope

/**
 * Created by Dylan on 28-Feb-17.
 */
@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope