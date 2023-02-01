package com.example.dictionaryapp.di

import android.content.Context
import androidx.room.Room
import com.example.dictionaryapp.BuildConfig
import com.example.dictionaryappcleanarchitecture.data.util.GsonParser
import com.google.gson.Gson
import com.quangnh.data.datasource.local.converters.Converters
import com.quangnh.data.datasource.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by quangnh
 * Date: 1/2/2023
 * Time: 10:29 PM
 * Project DictionaryApp
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

}