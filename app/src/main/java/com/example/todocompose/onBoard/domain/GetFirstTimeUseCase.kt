package com.example.todocompose.onBoard.domain

import com.example.todocompose.onBoard.data.repository.Repository
import javax.inject.Inject

class GetFirstTimeUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() = repository.getFirstTime()

}