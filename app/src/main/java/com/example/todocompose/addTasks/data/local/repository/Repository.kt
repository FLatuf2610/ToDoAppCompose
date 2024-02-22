package com.example.todocompose.addTasks.data.local.repository

import com.example.todocompose.addTasks.data.local.room.dao.TasksDao
import com.example.todocompose.addTasks.data.local.room.entities.toDomain
import com.example.todocompose.addTasks.ui.models.TaskModel
import com.example.todocompose.addTasks.ui.models.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val dao: TasksDao) {

    suspend fun getTasks() :Flow<List<TaskModel>> {
        return withContext(Dispatchers.IO){
            val result = dao.getTasks()
                 result.map { tasks -> tasks.map { it.toDomain() } }
        }
    }

    suspend fun addTask(taskModel: TaskModel){
        val taskEntity = taskModel.toEntity()
        return withContext(Dispatchers.IO){
            dao.insertTask(taskEntity)
        }
    }

    suspend fun deleteTask(taskModel: TaskModel){
        val taskEntity = taskModel.toEntity()
        return withContext(Dispatchers.IO){
            dao.deleteTask(taskEntity)
        }
    }

    suspend fun editTask(taskModel: TaskModel){
        val taskEntity = taskModel.toEntity()
        return withContext(Dispatchers.IO){
            dao.editTask(taskEntity)
        }
    }



}

