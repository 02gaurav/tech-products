package com.example.sprinklerassignement

import com.example.sprinklerassignement.di.scopes.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    @ActivityScope
    abstract fun bindMainPresenter(presenter: MainPresenter): MainContract.Presenter
}