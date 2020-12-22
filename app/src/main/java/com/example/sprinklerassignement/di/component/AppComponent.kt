package com.example.sprinklerassignement.di.component

import android.app.Application
import com.example.sprinklerassignement.MyApplication
import com.example.sprinklerassignement.di.builder.ActivityBindingModule
import com.example.sprinklerassignement.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class))
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application):Builder

        fun build(): AppComponent

    }
}