package com.example.surveyapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.surveyapplication.ui.theme.SurveyApplicationTheme

class MainActivity : ComponentActivity() {
    private lateinit var databaseHelper: SurveyDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = SurveyDatabaseHelper(this)
        setContent {
            FormScreen(this, databaseHelper)
        }
    }
}

@Composable
fun FormScreen(context: Context, databaseHelper: SurveyDatabaseHelper) {
    // Background image
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            alpha = 0.1f,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Form Content
        Column(
            modifier = Modifier
                .padding(24.dp)
                .background(
                    color = Color.White.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(12.dp)
                )
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Title
            Text(
                text = "Survey on Cancers",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25b897),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Name Input
            var name by remember { mutableStateOf("") }
            FormInputField(value = name, label = "Name") { name = it }

            // Age Input
            var age by remember { mutableStateOf("") }
            FormInputField(value = age, label = "Age") { age = it }

            // Mobile Number Input
            var mobileNumber by remember { mutableStateOf("") }
            FormInputField(value = mobileNumber, label = "Mobile Number") { mobileNumber = it }

            // Gender Selection
            val genderOptions = listOf("Male", "Female", "Other")
            var selectedGender by remember { mutableStateOf("") }
            RadioGroup(
                label = "Gender",
                options = genderOptions,
                selectedOption = selectedGender,
                onSelectedChange = { selectedGender = it }
            )

            // Cancers Selection
            val diabeticsOptions = listOf("Cancer", "Non Cancer")
            var selectedDiabetics by remember { mutableStateOf("") }
            RadioGroup(
                label = "Cancers",
                options = diabeticsOptions,
                selectedOption = selectedDiabetics,
                onSelectedChange = { selectedDiabetics = it }
            )

            // Error Message
            var error by remember { mutableStateOf("") }
            if (error.isNotEmpty()) {
                Text(
                    text = error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Submit Button
            Button(
                onClick = {
                    if (name.isNotEmpty() && age.isNotEmpty() && mobileNumber.isNotEmpty() && selectedGender.isNotEmpty() && selectedDiabetics.isNotEmpty()) {
                        val survey = Survey(
                            id = null,
                            name = name,
                            age = age,
                            mobileNumber = mobileNumber,
                            gender = selectedGender,
                            diabetics = selectedDiabetics
                        )
                        databaseHelper.insertSurvey(survey)
                        error = "Survey Completed"
                    } else {
                        error = "Please fill all fields"
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF84adb8)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text(text = "Submit", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun FormInputField(value: String, label: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = "$label :", fontSize = 18.sp, color = Color.Gray)
        TextField(
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color(0xFF25b897),
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RadioGroup(
    label: String,
    options: List<String>,
    selectedOption: String,
    onSelectedChange: (String) -> Unit
) {
    Column {
        Text(text = "$label :", fontSize = 18.sp, color = Color.Gray)
        options.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onSelectedChange(option) }
                )
                Text(
                    text = option,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
