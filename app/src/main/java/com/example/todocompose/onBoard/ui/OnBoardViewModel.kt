package com.example.todocompose.onBoard.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.todocompose.common.Constants
import com.example.todocompose.onBoard.domain.ChangeFirstTimeUseCase
import com.example.todocompose.onBoard.domain.SaveNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(private val saveNameUseCase: SaveNameUseCase,
    private val changeFirstTimeUseCase: ChangeFirstTimeUseCase)
    :ViewModel(){

    fun finishOnBoard(navController: NavController, name: String){
        Log.println(Log.ASSERT, "DATA STORE", name)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                saveNameUseCase(name)
                changeFirstTimeUseCase()
            }
            navController.navigate(Constants.TO_DO_SCREEN)
        }
    }

}