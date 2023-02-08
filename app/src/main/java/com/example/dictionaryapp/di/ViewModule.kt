package com.example.dictionaryapp.di

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dictionaryapp.features.history.adapter.HistoryWordAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class ViewModule {

    @Provides
    fun provideGridLayoutManager(activity: FragmentActivity) = GridLayoutManager(activity, 2)

    @Provides
    fun provideCustomStaggeredGridLayoutManager(): StaggeredGridLayoutManager {
        return StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    @Provides
    fun provideLinearLayoutManager(fragmentActivity: FragmentActivity) = LinearLayoutManager(fragmentActivity)

    @Provides
    fun provideHistoryWordAdapter() = HistoryWordAdapter()

}