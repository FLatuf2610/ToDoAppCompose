package com.example.todocompose.addTasks.ui.addTaskScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.addTasks.domain.InsertTaskInDbUseCase
import com.example.todocompose.addTasks.ui.models.TaskModel
import com.example.todocompose.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(private val insertTaskInDbUseCase: InsertTaskInDbUseCase)  :ViewModel(){

    private val _isDialogFromOpened = MutableStateFlow(false)
    val isDialogFromOpened: StateFlow<Boolean> = _isDialogFromOpened

    private val _isDialogToOpened = MutableStateFlow(false)
    val isDialogToOpened: StateFlow<Boolean> = _isDialogToOpened

    var hourFrom = mutableStateOf("")
        private set
    var hourTo = mutableStateOf("")
        private set

    fun addTask(title: String, desc: String, hourFrom: String, hourTo: String){
        val task = TaskModel(
            task = title,
            description = desc,
            hourFrom = hourFrom,
            hourTo = hourTo
        )
        viewModelScope.launch {
            insertTaskInDbUseCase(task)
        }
    }

    fun toggleFromDialog(){
        _isDialogFromOpened.value = !_isDialogFromOpened.value
    }
    fun toggleToDialog(){
        _isDialogToOpened.value = !_isDialogToOpened.value
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun setHourFromDialog(state: TimePickerState, dialog: Int = 0){
        val hourString = "${state.hour}:${state.minute}"
        if (dialog != 0) hourTo.value = Constants.convertHour(hourString)
        else hourFrom.value = Constants.convertHour(hourString)
    }

}