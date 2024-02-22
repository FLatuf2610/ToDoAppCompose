package com.example.todocompose.onBoard.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.todocompose.common.Constants
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = "user_data")

class DataStore @Inject constructor(private val context: Context) {
    suspend fun saveName(value: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(Constants.USER_NAME)] = value
        }
    }

    suspend fun updateFirstTime() {
        context.dataStore.edit {
            it[booleanPreferencesKey(Constants.IS_FIRST_TIME)] = false
        }
    }

    val getName = context.dataStore.data.map {
        it[stringPreferencesKey(Constants.USER_NAME)] ?: ""
    }

    val getFirstTime = context.dataStore.data.map {
        it[booleanPreferencesKey(Constants.IS_FIRST_TIME)] ?: true
    }

}