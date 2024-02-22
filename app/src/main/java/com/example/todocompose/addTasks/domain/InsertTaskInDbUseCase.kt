package com.example.todocompose.addTasks.domain

import androidx.room.Insert
import com.example.todocompose.addTasks.data.local.repository.Repository
import com.example.todocompose.addTasks.ui.models.TaskModel
import com.example.todocompose.addTasks.ui.models.toEntity
import javax.inject.Inject

class InsertTaskInDbUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(task:TaskModel) = repository.addTask(task)

}