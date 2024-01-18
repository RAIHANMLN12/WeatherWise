package com.poison.weatherwise.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.poison.weatherwise.R



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navigateToHomeScreen: () -> Unit
) {
    var currentPage by remember {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    val pagerState = rememberPagerState(
        pageCount = {
            3
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
        ) { page ->
            when (page) {
                0 -> OnboardingPage1(
                    pagerState = pagerState
                )
                1 -> OnboardingPage2(
                    pagerState = pagerState
                )
                2 -> OnboardingPage3(
                    pagerState = pagerState,
                    getStarted = {
                        navigateToHomeScreen()
                    }
                )
            }
        }

        DisposableEffect(pagerState.currentPage) {
            currentPage = pagerState.currentPage
            onDispose { }
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage1(
    pagerState: PagerState
) {
    OnboardingPage(
        imageResource = R.drawable.image1,
        title = "Welcome to WeatherWise",
        description = "Explore real-time weather updates, detailed forecasts, and interactive features with WeatherWise. Let's get started to bring the world of weather to your fingertips!",
        pagerState = pagerState,
        getStarted = {}
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage2(
    pagerState: PagerState
) {
    OnboardingPage(
        imageResource = R.drawable.image2,
        title = "Stay Informed Anytime, Anywhere",
        description = "WeatherWise provides you with up-to-date information on temperature, humidity, wind speed, and more. Visualize trends with intuitive charts and stay ahead of the weather. Swipe left to discover more!" ,
        showFinishButton = false,
        pagerState = pagerState,
        getStarted = {}
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage3(
    pagerState: PagerState,
    getStarted: () -> Unit
) {
    OnboardingPage(
        imageResource = R.drawable.image3,
        title = "Personalize Your Experience",
        description = "Let's begin by entering your current city or allowing us to access your device location.",
        showFinishButton = true,
        pagerState = pagerState,
        getStarted = {
            getStarted()
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage(
    imageResource: Int,
    title: String,
    description: String,
    showFinishButton: Boolean = false,
    pagerState: PagerState,
    getStarted: () -> Unit
) {
    var location by remember {
        mutableStateOf("")
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        OnboardingPagerIndicator(pagerState = pagerState)
        Spacer(modifier = Modifier.height(40.dp))
        if (showFinishButton) {
            Button(
                onClick = {
                    getStarted()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2A9D8F)
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    DotsIndicator(
        modifier = modifier.then(Modifier.padding(top = 16.dp)),
        pagerState = pagerState,
        activeDotColor = Color(0xFF2A9D8F),
        inactiveDotColor = Color.Gray
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    activeDotColor: Color,
    inactiveDotColor: Color
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(pagerState.pageCount) { index ->
                val color = if (index == pagerState.currentPage) activeDotColor else inactiveDotColor
                Dot(color = color)
            }
        }
    }
}

@Composable
fun Dot(
    color: Color
) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .clip(MaterialTheme.shapes.small)
            .background(color)
    )
}

@Preview
@Composable
fun OnBoardingScreenPreview(){
    OnboardingScreen(
        navigateToHomeScreen = {}
    )
}

