package com.example.todocompose.onBoard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todocompose.R
import com.example.todocompose.common.MyButton
import com.example.todocompose.common.OnBoardTextField


@Composable
fun OnBoardScreen(navController: NavController, onBoardViewModel: OnBoardViewModel){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ){
        Column(
            Modifier
                .align(Alignment.Center)
        ) {
            Image(painter = painterResource(R.drawable.on_board_svg),
                contentDescription = "Illustration", modifier = Modifier.scale(0.8f))
            Spacer(modifier = Modifier.height(16.dp))
            Text(fontSize = 45.sp,text = "Plan your tasks every day",
                color = Color.White,
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,
                letterSpacing = 3.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Plan, manage and track all your tasks in one flexible app",
                fontSize = 20.sp, color = Color.LightGray, textAlign = TextAlign.Center,
                lineHeight = 32.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            var name by rememberSaveable { mutableStateOf("") }
            OnBoardTextField(value = name, onTextChange ={ name = it } ,
                placeHolder = "Your name",modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            MyButton(onClick = { onBoardViewModel.finishOnBoard(navController, name) },
                modifier = Modifier.fillMaxWidth(), text = "Get Started", isEnabled = name.trim().isNotBlank())
        }
    }
}

@Preview
@Composable
fun PreviewOnBoard(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ){
        Column(
            Modifier
                .align(Alignment.Center)
        ) {
            Image(painter = painterResource(R.drawable.on_board_svg),
                contentDescription = "Illustration", modifier = Modifier.scale(0.8f))
            Spacer(modifier = Modifier.height(16.dp))
            Text(fontSize = 45.sp,text = "Plan your tasks every day",
                color = Color.White,
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,
                letterSpacing = 3.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Plan, manage and track all your tasks in one flexible app",
                fontSize = 20.sp, color = Color.LightGray, textAlign = TextAlign.Center,
                lineHeight = 32.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "", onValueChange = {},
                placeholder = { Text(text = "Your name") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Black,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.LightGray
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(vertical = 20.dp),
                shape = RoundedCornerShape(12.dp)
                ) {
                Text(text = "Get Started", fontSize = 18.sp, fontWeight = FontWeight.W700)
            }

        }
    }
}