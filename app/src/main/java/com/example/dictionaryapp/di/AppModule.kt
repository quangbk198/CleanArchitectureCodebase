package com.example.dictionaryapp.di

import com.quangnh.core.resource.sharepref.AppPreference
import com.quangnh.core.resource.sharepref.AppPreferenceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.inflationx.calligraphy3.CalligraphyConfig
import javax.inject.Singleton

/**
 * Created by quangnh
 * Date: 1/2/2023
 * Time: 10:29 PM
 * Project DictionaryApp
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/open_sans/opensans_regular.ttf")
            .setFontAttrId(io.github.inflationx.calligraphy3.R.attr.fontPath)
            .build()
    }

    @Provides
    @Singleton
    fun provideAppPreference(appPreferenceImpl: AppPreferenceImpl): AppPreference {
        return appPreferenceImpl
    }

}