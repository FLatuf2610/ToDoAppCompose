package com.example.todocompose.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoardTextField(value :String, onTextChange :(String) -> Unit
                ,placeHolder :String,modifier: Modifier){
    OutlinedTextField(
        value = value, onValueChange = { onTextChange(it) },
        placeholder = { Text(text = placeHolder) },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.Black,
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedBorderColor = Color.Black,
            focusedBorderColor = Color.LightGray
        ),
        modifier = modifier
    )
}

@Composable
fun MyButton(onClick :() -> Unit, modifier: Modifier,
             text: String, isEnabled: Boolean = true){
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.LightGray
        ),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        enabled = isEnabled
    ) {
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.W700)
    }
}

@Composable
fun AddTaskTextField(value: String, onTextChange: (String) -> Unit,
                     placeHolder: String, singleLine: Boolean = false,
                     maxLines: Int = 1, modifier: Modifier,
                     keyboardType: KeyboardType = KeyboardType.Text,
                     helperText: String = "", readOnly: Boolean = false,
                     enabled: Boolean = true){
    OutlinedTextField(
        value = value, onValueChange = { onTextChange(it) },
        placeholder = { Text(text = placeHolder) },
        singleLine = singleLine,
        maxLines = maxLines,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.Black,
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledBorderColor = Color.White,
            disabledPlaceholderColor = Color.DarkGray
        ),
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        supportingText = { Text(text = helperText) },
        readOnly = readOnly,
        enabled = enabled
    )
}
