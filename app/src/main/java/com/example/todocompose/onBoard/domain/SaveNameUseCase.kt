package com.example.todocompose.onBoard.domain

import com.example.todocompose.onBoard.data.repository.Repository
import javax.inject.Inject

class SaveNameUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(name :String) = repository.saveName(name)

}