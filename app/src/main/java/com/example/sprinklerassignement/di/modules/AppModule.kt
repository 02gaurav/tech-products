package com.example.sprinklerassignement.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.sprinklerassignement.AppDataBase
import com.example.sprinklerassignement.schedulers.AppSchedulerProvider
import com.example.sprinklerassignement.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    private val DB_NAME = "product_data"

    @Provides
    @Singleton
    internal fun provideSchedulers(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    internal fun provideAppContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


}