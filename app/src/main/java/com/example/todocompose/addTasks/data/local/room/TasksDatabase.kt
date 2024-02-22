package com.example.todocompose.addTasks.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todocompose.addTasks.data.local.room.dao.TasksDao
import com.example.todocompose.addTasks.data.local.room.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TasksDatabase :RoomDatabase(){
    abstract fun tasksDao():TasksDao
}