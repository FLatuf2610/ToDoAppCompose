package com.example.todocompose.addTasks.domain

import com.example.todocompose.addTasks.data.local.repository.Repository
import com.example.todocompose.addTasks.ui.models.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(taskModel: TaskModel) =  repository.deleteTask(taskModel)
}