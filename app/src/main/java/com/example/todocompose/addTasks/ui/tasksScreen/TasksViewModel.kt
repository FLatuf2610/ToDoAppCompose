package com.example.todocompose.addTasks.ui.tasksScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.addTasks.domain.DeleteTaskUseCase
import com.example.todocompose.addTasks.domain.EditTaskInDbUseCase
import com.example.todocompose.addTasks.domain.GetTasksFromDbUseCase
import com.example.todocompose.addTasks.ui.models.TaskModel
import com.example.todocompose.onBoard.domain.GetNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val getTasksFromDbUseCase: GetTasksFromDbUseCase,
                                         private val getNameUseCase: GetNameUseCase,
                                         private val editTaskInDbUseCase: EditTaskInDbUseCase,
                                         private val deleteTaskUseCase: DeleteTaskUseCase) :ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskModel>>(emptyList())
    val tasks:StateFlow<List<TaskModel>> = _tasks

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _isDialogShowed = MutableStateFlow(false)
    val isDialogShowed: StateFlow<Boolean> = _isDialogShowed

    fun onCreateViewmodel(){
        viewModelScope.launch {
           combine(
               getTasksFromDbUseCase(),
               getNameUseCase()
           ) {
               listTasks, nameFlow ->
               _tasks.value = listTasks
               _name.value = nameFlow
           }.collect()
        }
    }

    fun toggleDialog(task: TaskModel){
        viewModelScope.launch {
            val newTask = task.copy(isModalShowed = !task.isModalShowed)
            editTaskInDbUseCase(newTask)
        }
    }

    fun deleteTask(task: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
        /*val index = _tasks.value.indexOf(task)
        val newList = _tasks.value.toMutableList()
        newList.removeAt(index)
        _tasks.value = newList*/
    }

    fun toggleCheckTask(task: TaskModel){
        viewModelScope.launch {
            editTaskInDbUseCase(task.copy(isSelected = !task.isSelected))
        }
        /*val index = _tasks.value.indexOf(task)
        val newList = _tasks.value.toMutableList()
        newList[index] = newList[index].copy(isSelected = !task.isSelected)
        _tasks.value = newList*/
    }

}