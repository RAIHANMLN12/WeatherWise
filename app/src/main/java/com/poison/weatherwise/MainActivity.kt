package com.poison.weatherwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.poison.weatherwise.theme.WeatherWiseTheme
import com.poison.weatherwise.ui.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherWiseTheme {
        HomeScreen()
    }
}