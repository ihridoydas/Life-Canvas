package info.hridoydas.lifecanvas.common.introOnBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import info.hridoydas.lifecanvas.common.R
import info.hridoydas.lifecanvas.common.ScreenDestinations
import info.hridoydas.lifecanvas.common.introOnBoarding.showCase.IntroShowCaseScaffold
import info.hridoydas.lifecanvas.common.introOnBoarding.showCase.ShowcaseStyle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController, onBackPress : ()->Unit) {

    // スクロールの行動 (scrollBehavior)
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    val scope= rememberCoroutineScope()
    //OnBoarding ViewModel
   // val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()

    var showSkipAppIntro by remember {
        mutableStateOf(true)
    }
//    val showSkipShowOrNot = runBlocking {
//        onBoardingViewModel.getOnSkipCaseCompleted.first()
//    }
    IntroShowCaseScaffold(
//        showIntroShowCase = showSkipShowOrNot ?: false,
        showIntroShowCase = true,
        onShowCaseCompleted = {
            //App Intro finished!!
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
                        containerColor = Color(0xFF1E1E1E),
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        scrolledContainerColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
//                                onBoardingViewModel.setOnSkipCaseCompleted(false)
                                navController?.navigate(ScreenDestinations.HomeScreen.route) {
                                    popUpTo(ScreenDestinations.OnBoarding.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            modifier = Modifier
                                .introShowCaseTarget(
                                    index = 0,
                                    style = ShowcaseStyle.Default.copy(
                                        backgroundColor = Color(0xFF9AD0EC), // specify color of background
                                        backgroundAlpha = 0.98f, // specify transparency of background
                                        targetCircleColor = Color.White // specify color of target circle
                                    ),
                                    content = {
                                        Column {
                                            Image(
                                                painterResource(id = R.drawable.one),
                                                contentDescription = null,
                                                modifier = Modifier.size(80.dp)
                                            )

                                            Text(
                                                text = "Skip Intro if you want to.",
                                                color = Color.Black,
                                                fontSize = 24.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "You can skip the intro by clicking here.",
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }
                                    }
                                )
                        ) {
                            Text(text = "SKIP", modifier = Modifier)
                        }
                    }
                )
            },
            content = {
                Column(
                    Modifier
                        .padding(it)
                        .background(Color(0xFF1E1E1E))
                        .fillMaxSize()
                ) {

                    val items = OnBoardingItem.get()
                    val state = rememberPagerState(
                        initialPage = 0,
                        initialPageOffsetFraction = 0f
                    ) {
                        items.size
                    }

                    HorizontalPager(
                        state = state,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(0.8f)
                    ) { page ->

                        //get items into Item
                        OnBoardingItem(items[page],size = items.size, index = state.currentPage)

                    }

                    BottomSection(
                        navController = navController,
                        // onBoardingViewModel = onBoardingViewModel,
                        size = items.size, index = state.currentPage,
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
                        }
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
        onBackPress = {}
    )
}

