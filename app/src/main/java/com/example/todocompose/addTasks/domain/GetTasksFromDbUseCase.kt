package com.example.todocompose.addTasks.domain

import com.example.todocompose.addTasks.data.local.repository.Repository
import com.example.todocompose.addTasks.ui.models.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksFromDbUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke() :Flow<List<TaskModel>> = repository.getTasks()
}