package com.example.todocompose.onBoard.domain

import com.example.todocompose.onBoard.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNameUseCase @Inject constructor(private val repository: Repository){

    operator fun invoke() : Flow<String> = repository.getName()

}