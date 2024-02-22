package com.example.todocompose.addTasks.ui.models

import com.example.todocompose.addTasks.data.local.room.entities.TaskEntity

data class TaskModel(
    val task: String,
    val id: Long = System.currentTimeMillis(),
    var isSelected: Boolean = false,
    val description: String = "",
    val hourFrom: String = "",
    val hourTo: String = "",
    val isModalShowed: Boolean = false
)
fun TaskModel.toEntity() :TaskEntity {
    return TaskEntity(
        task = task,
        id = id,
        isSelected = isSelected,
        description = description,
        hourFrom = hourFrom,
        hourTo = hourTo,
        isModalShowed = isModalShowed
    )
}
