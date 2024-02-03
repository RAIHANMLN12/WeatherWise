package com.poison.weatherwise.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.poison.weatherwise.viewModel.WeatherViewModel


val horizontalPaddingValue = 20.dp
var city = mutableStateOf("")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(weatherViewModel: WeatherViewModel){
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPaddingValue)
    ) {
        OutlinedTextField(
            value = city.value, 
            onValueChange = {
                city.value = it
            },
            placeholder = {
                Text(
                    text = "Search a city",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedLeadingIconColor = Color.Black,
                unfocusedLeadingIconColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    weatherViewModel.getWeatherByCity(city.value)
                    keyboardController?.hide()
                }
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun HomeScreen(){
    val weatherViewModel: WeatherViewModel = viewModel()
    val weather by weatherViewModel.weather.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchField(weatherViewModel)
        Spacer(modifier = Modifier.height(30.dp))
        weather?.let {
            AsyncImage(
                model = weatherViewModel.getImageUrl(it.weather[0].icon),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = it.weather[0].main,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}
