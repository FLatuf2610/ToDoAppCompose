package com.example.todocompose.addTasks.ui.tasksScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.todocompose.addTasks.ui.models.TaskModel
import com.example.todocompose.common.Constants


@Composable
fun TasksScreen(tasksViewModel: TasksViewModel, navController: NavController, name: String) {
    LaunchedEffect(key1 = true){
        tasksViewModel.onCreateViewModel()
    }

    val tasks by tasksViewModel.tasks.collectAsState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(Constants.ADD_SCREEN) },
                contentColor = Color.Black,
                containerColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                Text(text = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { pad ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .background(Color.Black)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(8.dp)
            ) {
                Text(
                    text = if (name.isNotBlank())"Hello ${name}!" else "Hello!",
                    color = Color.White, fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    modifier = Modifier.padding(top = 64.dp, start = 24.dp)
                )
                Text(text = if (tasks.isNotEmpty())"You have ${tasks.size} tasks to complete"
                    else "You don´t have tasks!", color = Color.LightGray,
                    modifier = Modifier.padding(start = 28.dp), fontSize = 18.sp)
                Spacer(modifier = Modifier.height(40.dp))
                LazyColumn {
                    items(tasks){ task ->
                        ItemTask(task = task, tasksViewModel)
                        if (task.isModalShowed){
                            DeleteDialog(tasksViewModel = tasksViewModel, task = task)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemTask(task: TaskModel, tasksViewModel: TasksViewModel){
    val boxColor by animateColorAsState(
        when (task.isSelected) {
            true -> Color.White
            false -> Color.Black
        }, label = ""
    )
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(onLongClick = { tasksViewModel.toggleDialog(task) },
                    onClick = {tasksViewModel.toggleCheckTask(task)}),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = task.hourFrom, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(boxColor, shape = CircleShape)
                    .border(
                        width = 3.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
            ){
                if (task.isSelected){
                    Icon(Icons.Default.Check, "", tint = Color.Black,
                        modifier = Modifier.fillMaxSize(1f)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .height(2.dp)
                    .background(Color.White)
            )
            Card(
                modifier = Modifier
                    .width(220.dp)
                    .focusable(true),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.Cyan)
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = task.task, fontWeight = FontWeight.W600,
                        fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.description,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(text = task.hourFrom, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "-", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = task.hourTo, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(Color.White)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }


}

@Composable
fun DeleteDialog(tasksViewModel: TasksViewModel, task: TaskModel){
    Dialog(onDismissRequest = { tasksViewModel.toggleDialog(task) },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true)){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Text(text = "Attention",
                fontWeight = FontWeight.Bold, color = Color.White,
                fontSize = 20.sp)
            Spacer(Modifier.height(32.dp))
            Text(text = "Are you sure you want to delete this task?",
                fontSize = 18.sp, color = Color.White)
            Spacer(Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {
                    tasksViewModel.deleteTask(task) },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)) {
                    Text(text = "YES")
                }
                TextButton(onClick = { tasksViewModel.toggleDialog(task) },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)) {
                    Text(text = "NO")
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewTasks(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(8.dp)
        ) {
            Text(
                text = "Hello Franco!", color = Color.White, fontWeight = FontWeight.Bold,
                fontSize = 35.sp, modifier = Modifier.padding(top = 64.dp, start = 24.dp)
            )
            Text(text = "You have 8 tasks to complete", color = Color.LightGray,
                modifier = Modifier.padding(start = 28.dp), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(40.dp))
            PreviewItem()
            PreviewItem()
        }
    }
}


@Composable
fun PreviewItem(){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "8:OO AM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .border(width = 3.dp, color = Color.White, shape = CircleShape)
            )
            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .height(2.dp)
                    .background(Color.White)
            )
            Card(
                modifier = Modifier
                    .width(220.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.Cyan)
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Title of the Task", fontWeight = FontWeight.W600,
                        fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "The content and explanation of the task, it could have maybe 3 lines max",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(text = "8:00 AM", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "-", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "10:00 AM", fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(Color.White)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }

}

@Preview
@Composable
fun PreviewDeleteDialog(){
    Dialog(onDismissRequest = { },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true)){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Text(text = "Attention",
                    fontWeight = FontWeight.Bold, color = Color.White,
                    fontSize = 20.sp)
                Spacer(Modifier.height(32.dp))
                Text(text = "Are you sure you want to delete this task?",
                    fontSize = 18.sp, color = Color.White)
                Spacer(Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)) {
                        Text(text = "YES")
                    }
                    TextButton(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.White)) {
                        Text(text = "NO")
                    }
                }
            }
    }
}





