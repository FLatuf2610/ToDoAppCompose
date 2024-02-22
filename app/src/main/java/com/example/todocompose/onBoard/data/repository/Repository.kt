package com.example.todocompose.onBoard.data.repository

import com.example.todocompose.onBoard.data.dataStore.DataStore
import javax.inject.Inject

class Repository @Inject constructor(private val dataStore: DataStore) {

    fun getName() = dataStore.getName

    fun getFirstTime() = dataStore.getFirstTime

    suspend fun saveName(name:String) = dataStore.saveName(name)

    suspend fun updateFirstTime() = dataStore.updateFirstTime()

}