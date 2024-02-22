package com.example.todocompose.onBoard.domain

import com.example.todocompose.onBoard.data.repository.Repository
import javax.inject.Inject

class ChangeFirstTimeUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() = repository.updateFirstTime()

}