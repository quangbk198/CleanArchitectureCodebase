package com.example.dictionaryapp.di

import com.quangnh.domain.repository.word.WordInfoRepository
import com.quangnh.domain.repository.word.WordInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by quangnh
 * Date: 1/2/2023
 * Time: 10:30 PM
 * Project DictionaryApp
 */
@InstallIn(ViewModelComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideWordInfoRepository(wordInfoRepositoryImpl: WordInfoRepositoryImpl): WordInfoRepository

}