package com.example.surveyapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.surveyapplication.ui.theme.SurveyApplicationTheme

class AdminActivity : ComponentActivity() {
    private lateinit var databaseHelper: SurveyDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = SurveyDatabaseHelper(this)
        setContent {
            val data = databaseHelper.getAllSurveys()
            Log.d("swathi", data.toString())
            val survey = databaseHelper.getAllSurveys()
            ListListScopeSample(survey)
        }
    }
}

@Composable
fun ListListScopeSample(survey: List<Survey>) {

    // Background Image with Proper Placement
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            alpha = 0.3F,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Title Section
            Text(
                text = "Survey Details",
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Survey Details with LazyColumn
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(survey) { item ->
                    SurveyCard(item)
                }
            }
        }
    }
}

@Composable
fun SurveyCard(survey: Survey) {
    Surface(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Name: ${survey.name}",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Age: ${survey.age}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Mobile Number: ${survey.mobileNumber}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Gender: ${survey.gender}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Diabetics: ${survey.diabetics}",
                style = MaterialTheme.typography.body1
            )
        }
    }
}
