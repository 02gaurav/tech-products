package com.example.sprinklerassignement.base

import android.content.Context
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseMvpActivity<V: MvpView>: DaggerAppCompatActivity(), MvpView {

    abstract fun getPresenter(): MvpPresenter<V>

    override val viewContext: Context?
        get() = this

}