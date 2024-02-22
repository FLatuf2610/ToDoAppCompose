package com.example.todocompose.onBoard.data.di

import android.content.Context
import com.example.todocompose.onBoard.data.dataStore.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatStoreModule {

    @Singleton
    @Provides
    fun providesDataStoreRepo(
        @ApplicationContext context: Context
    ) = DataStore(context)
}