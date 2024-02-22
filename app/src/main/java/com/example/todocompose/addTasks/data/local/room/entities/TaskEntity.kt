package com.example.todocompose.addTasks.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todocompose.addTasks.ui.models.TaskModel

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("task")
    val task: String,
    @ColumnInfo("is_selected")
    val isSelected: Boolean,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("hour_from")
    val hourFrom: String,
    @ColumnInfo("hour_to")
    val hourTo: String,
    @ColumnInfo("is_modal_showed")
    val isModalShowed: Boolean
)
fun TaskEntity.toDomain() :TaskModel{
    return TaskModel(
        id = id,
        task = task,
        isSelected = isSelected,
        description = description,
        hourFrom = hourFrom,
        hourTo = hourTo,
        isModalShowed = isModalShowed
    )
}
