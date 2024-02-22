package com.example.todocompose.addTasks.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.todocompose.addTasks.data.local.room.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):TasksDatabase =
        Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            "tasks"
            ).build()

    @Singleton
    @Provides
    fun provideTasksDao(database: TasksDatabase) = database.tasksDao()
}