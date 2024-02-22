@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todocompose.addTasks.ui.addTaskScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.todocompose.R
import com.example.todocompose.common.AddTaskTextField
import com.example.todocompose.common.Constants
import com.example.todocompose.common.MyButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddScreen(addTaskViewmodel: AddTaskViewmodel, navController: NavController){
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    val hourFrom by addTaskViewmodel.hourFrom
    val hourTo by addTaskViewmodel.hourTo
    val openDialogFrom by addTaskViewmodel.isDialogFromOpened.collectAsState()
    val openDialogTo by addTaskViewmodel.isDialogToOpened.collectAsState()
    Scaffold(
        topBar = { TopAppBar( title = {},
            navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBackIosNew, "")
            } },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black, navigationIconContentColor = Color.White
            ),
        ) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(it)
        ){
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(scrollState, true)
            ) {
                Image(painter = painterResource(id = R.drawable.undraw_add_information_j2wg),
                    contentDescription = "", modifier = Modifier.scale(1f))
                Text(text = "Add Your Tasks", fontSize = 30.sp,
                    fontWeight = FontWeight.Bold, color = Color.White,
                    modifier = Modifier.padding(top = 48.dp, start = 24.dp, bottom = 48.dp))
                AddTaskTextField(
                    value = title,
                    onTextChange = { title = it },
                    placeHolder = "Title",
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    helperText = "Required"
                )
                Spacer(modifier = Modifier.height(24.dp))
                AddTaskTextField(
                    value = description,
                    onTextChange = { description = it },
                    placeHolder = "Description",
                    maxLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    helperText = ""
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                ) {
                    AddTaskTextField(
                        value = hourFrom,
                        onTextChange = {},
                        placeHolder = "From Hour",
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        keyboardType = KeyboardType.Number,
                        readOnly = true
                    )
                    TextButton(onClick = { addTaskViewmodel.toggleFromDialog() },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.White
                        )) {
                        Text(text = "Pick Hour", fontSize = 18.sp)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                ) {
                    AddTaskTextField(
                        value = hourTo,
                        onTextChange = {},
                        placeHolder = "To Hour",
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        keyboardType = KeyboardType.Number,
                        readOnly = true
                    )
                    TextButton(onClick = { addTaskViewmodel.toggleToDialog() },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.White
                        )) {
                        Text(text = "Pick Hour", fontSize = 18.sp)
                    }
                }
                
                MyButton(onClick = { addTaskViewmodel.addTask(title, description, hourFrom, hourTo)
                                   navController.popBackStack()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "Add Task",
                    isEnabled = title.trim().isNotBlank() && hourTo.trim().isNotBlank() && hourFrom.trim().isNotBlank())
            }
        }
        MyDialog(addTaskViewmodel = addTaskViewmodel, isOpen = openDialogFrom)
        MyDialog(addTaskViewmodel = addTaskViewmodel, isOpen = openDialogTo, 1)

    }
}

@Composable
fun  MyDialog(addTaskViewmodel: AddTaskViewmodel, isOpen: Boolean, typeDialog: Int = 0){
    val timePickerState = rememberTimePickerState()
    if (isOpen) {
        Dialog(onDismissRequest = { if (typeDialog != 0) addTaskViewmodel.toggleToDialog()
        else addTaskViewmodel.toggleFromDialog()},
            properties = DialogProperties(dismissOnClickOutside = true)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black)
                    .padding(16.dp),
            ) {
                Text(text = "Pick Your task Hour",
                    fontSize = 30.sp,color = Color.White,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                TimePicker(state = timePickerState,
                    modifier = Modifier.scale(0.75f),
                    colors = TimePickerDefaults.colors(
                        selectorColor = Color.Black,
                        periodSelectorUnselectedContainerColor = Color.White,
                        periodSelectorSelectedContainerColor = Color.Black,
                        periodSelectorSelectedContentColor = Color.White,
                        timeSelectorSelectedContainerColor = Color.White,
                        timeSelectorSelectedContentColor = Color.Black,
                        timeSelectorUnselectedContainerColor = Color.White,
                        timeSelectorUnselectedContentColor = Color.Black
                    ))
                MyButton(onClick = {
                    if (typeDialog != 0) {
                        addTaskViewmodel.setHourFromDialog(timePickerState, 1)
                        addTaskViewmodel.toggleToDialog()
                    }
                    else {
                        addTaskViewmodel.setHourFromDialog(timePickerState)
                        addTaskViewmodel.toggleFromDialog()
                    }},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "Done")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun PreviewAdd(){
    Scaffold(
        topBar = { TopAppBar( title = {},
            navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ArrowBackIosNew, "")
            } },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black, navigationIconContentColor = Color.White
            ),
        ) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(it)
        ){
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(scrollState, true)
            ) {
                Image(painter = painterResource(id = R.drawable.undraw_add_information_j2wg),
                    contentDescription = "", modifier = Modifier.scale(1f))
                Text(text = "Add Your Tasks", fontSize = 30.sp,
                    fontWeight = FontWeight.Bold, color = Color.White,
                    modifier = Modifier.padding(top = 48.dp, start = 24.dp, bottom = 48.dp))
                AddTaskTextField(
                    value = "",
                    onTextChange = {},
                    placeHolder = "Title",
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    helperText = "Required"
                )
                Spacer(modifier = Modifier.height(24.dp))
                AddTaskTextField(
                    value = "",
                    onTextChange = {},
                    placeHolder = "Description",
                    maxLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    helperText = ""
                )
                Spacer(modifier = Modifier.height(24.dp))
                AddTaskTextField(
                    value = "",
                    onTextChange = {},
                    placeHolder = "Hour",
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clickable { },
                    keyboardType = KeyboardType.Number,
                    readOnly = true
                )
                MyButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "Add Task",
                    isEnabled = true)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyDialogPreview(){
    Dialog(onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(dismissOnClickOutside = true)) {
        val timePickerState = rememberTimePickerState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
                .padding(16.dp),
        ) {
            Text(text = "Pick your task hour",
                fontSize = 30.sp,color = Color.White,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            TimePicker(state = timePickerState,
                modifier = Modifier.scale(0.75f),
                colors = TimePickerDefaults.colors(
                    selectorColor = Color.Black,
                    periodSelectorUnselectedContainerColor = Color.White,
                    periodSelectorSelectedContainerColor = Color.Black,
                    periodSelectorSelectedContentColor = Color.White,
                    timeSelectorSelectedContainerColor = Color.White,
                    timeSelectorSelectedContentColor = Color.Black,
                    timeSelectorUnselectedContainerColor = Color.White,
                    timeSelectorUnselectedContentColor = Color.Black
                ))
            MyButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = "Done")
        }
    }
}



