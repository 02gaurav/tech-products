package com.example.sprinklerassignement.di.builder

import com.example.sprinklerassignement.MainActivity
import com.example.sprinklerassignement.MainModule
import com.example.sprinklerassignement.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    internal abstract fun bindMainActivity(): MainActivity

}