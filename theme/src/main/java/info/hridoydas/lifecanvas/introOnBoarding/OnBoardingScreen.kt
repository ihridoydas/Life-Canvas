/*
* MIT License
*
* Copyright (c) 2024 Hridoy Chandra Das
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package info.hridoydas.lifecanvas.introOnBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import info.hridoydas.lifecanvas.ScreenDestinations
import info.hridoydas.lifecanvas.common.R
import info.hridoydas.lifecanvas.introOnBoarding.showCase.IntroShowCaseScaffold
import info.hridoydas.lifecanvas.introOnBoarding.showCase.ShowcaseStyle
import info.hridoydas.lifecanvas.theme.backgroundColor
import info.hridoydas.lifecanvas.theme.introBackgroundColor
import kotlinx.coroutines.launch

const val SCREEN_WEIGHT = 0.8f

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    // OnBoarding ViewModel
    // val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()

    /*
        var showSkipAppIntro by remember {
               mutableStateOf(true)
           }
           val showSkipShowOrNot = runBlocking {
           //onBoardingViewModel.getOnSkipCaseCompleted.first()
           }
     */
    IntroShowCaseScaffold(
        // showIntroShowCase = showSkipShowOrNot ?: false,
        showIntroShowCase = true,
        onShowCaseCompleted = {
            // App Intro finished!!
            // showSkipAppIntro = false
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    title = { },
                    colors = TopAppBarColors(
                        containerColor = backgroundColor,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        scrolledContainerColor = Color.White,
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                // onBoardingViewModel.setOnSkipCaseCompleted(false)
                                navController.navigate(ScreenDestinations.HomeScreen.route) {
                                    popUpTo(ScreenDestinations.OnBoarding.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            modifier = Modifier.introShowCaseTarget(
                                index = 0,
                                style = ShowcaseStyle.Default.copy(
                                    backgroundColor = introBackgroundColor,
                                    backgroundAlpha = 0.98f,
                                    targetCircleColor = Color.White,
                                ),
                                content = {
                                    Column {
                                        Image(
                                            painterResource(id = R.drawable.one),
                                            contentDescription = null,
                                            modifier = Modifier.size(80.dp),
                                        )

                                        Text(
                                            text = "Skip Intro if you want to.",
                                            color = Color.Black,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Text(
                                            text = "You can skip the intro by clicking here.",
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                        )
                                    }
                                },
                            ),
                        ) {
                            Text(text = "SKIP", modifier = Modifier)
                        }
                    },
                )
            },
            content = {
                Column(
                    Modifier
                        .padding(it)
                        .background(backgroundColor)
                        .fillMaxSize(),
                ) {
                    val items = OnBoardingItem.get()
                    val state = rememberPagerState(
                        initialPage = 0,
                        initialPageOffsetFraction = 0f,
                    ) {
                        items.size
                    }

                    HorizontalPager(
                        state = state,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(SCREEN_WEIGHT),
                    ) { page ->

                        // get items into Item
                        OnBoardingItem(items[page], size = items.size, index = state.currentPage)
                    }

                    BottomSection(
                        navController = navController,
                        // onBoardingViewModel = onBoardingViewModel,
                        index = state.currentPage,
                        onBackClicked = {
                            if (state.currentPage > 0) { // Check if currentPage is not the first page
                                scope.launch {
                                    state.scrollToPage(state.currentPage - 1)
                                }
                            }
                        },
                        onNextClicked = {
                            if (state.currentPage < items.size - 1) { // Check if currentPage is not the last page
                                scope.launch {
                                    state.scrollToPage(state.currentPage + 1)
                                }
                            }
                        },
                    )
                }
            },
        )
    }
}

@Preview
@Composable
private fun OnBoardingPreview() {
    OnBoardingScreen(
        navController = rememberNavController(),
    )
}
