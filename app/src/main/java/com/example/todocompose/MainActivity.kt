package com.example.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todocompose.addTasks.ui.addTaskScreen.AddScreen
import com.example.todocompose.addTasks.ui.addTaskScreen.AddTaskViewModel
import com.example.todocompose.addTasks.ui.tasksScreen.TasksScreen
import com.example.todocompose.addTasks.ui.tasksScreen.TasksViewModel
import com.example.todocompose.common.Constants
import com.example.todocompose.onBoard.domain.GetFirstTimeUseCase
import com.example.todocompose.onBoard.domain.GetNameUseCase
import com.example.todocompose.onBoard.ui.OnBoardScreen
import com.example.todocompose.onBoard.ui.OnBoardViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val tasksViewModel : TasksViewModel by viewModels()
    private val onBoardViewModel : OnBoardViewModel by viewModels()
    private val addTaskViewModel :AddTaskViewModel by viewModels()
    @Inject lateinit var getFirstTimeUseCase :GetFirstTimeUseCase
    @Inject lateinit var getNameUseCase: GetNameUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(Color.Black)
            val navController = rememberNavController()

            var nameP by remember { mutableStateOf("") }
            LaunchedEffect(Unit){

                withContext(Dispatchers.IO){
                    combine(getFirstTimeUseCase(), getNameUseCase()){ firstTime, name ->
                        nameP = name
                        withContext(Dispatchers.Main){
                            if (firstTime) navController.navigate(Constants.ON_BOARD_SCREEN){
                                popUpTo(0)
                            }
                            else navController.navigate(Constants.TO_DO_SCREEN){
                                popUpTo(0)
                            }
                        }
                    }.collect()
                }
            }
            NavHost(navController = navController,
                startDestination = Constants.SPLASH_SCREEN){
                composable(Constants.SPLASH_SCREEN){
                    SplashScreen()
                }
                composable(Constants.ON_BOARD_SCREEN){
                    AnimatedVisibility(
                        visible = navController.currentBackStackEntry?.destination?.route == Constants.ON_BOARD_SCREEN,
                        enter = slideInVertically { -it },
                        exit = slideOutVertically { it / 2 }
                    ) {
                        OnBoardScreen(navController = navController, onBoardViewModel = onBoardViewModel)
                    }

                }
                composable(Constants.TO_DO_SCREEN){
                    AnimatedVisibility(
                        visible = navController.currentBackStackEntry?.destination?.route == Constants.TO_DO_SCREEN,
                        enter = slideInVertically { -it },
                        exit = slideOutVertically { it / 2}
                    ){
                        TasksScreen(tasksViewModel = tasksViewModel, navController = navController, name = nameP)
                    }
                }
                composable(Constants.ADD_SCREEN){
                    AnimatedVisibility(
                        visible = navController.currentBackStackEntry?.destination?.route == Constants.ADD_SCREEN,
                        enter = slideInVertically { -it },
                        exit = slideOutVertically { it / 2}
                    ){
                        AddScreen(addTaskViewModel = addTaskViewModel, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }
}

