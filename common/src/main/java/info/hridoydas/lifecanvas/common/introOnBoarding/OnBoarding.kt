package info.hridoydas.lifecanvas.common.introOnBoarding


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import info.hridoydas.lifecanvas.common.ScreenDestinations
import info.hridoydas.lifecanvas.theme.LifeCanvasTheme


@Composable
fun BottomSection(
    navController: NavHostController,
    //onBoardingViewModel : OnBoardingViewModel,
    size: Int,
    index: Int,
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {

        //back button
        Button(
            onClick = if (index != 0) {
                onBackClicked
            } else {
                {
//                    onBoardingViewModel.setOnSkipCaseCompleted(false)
//                    navController.navigate(ScreenDestinations.ViewScreen.route){
//                        popUpTo(ScreenDestinations.HomeScreen.route) {
//                            inclusive = true
//                        }
//                    }
                }
            },
            modifier = Modifier
                .align(CenterStart)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color(0xFF6F6F6F),
            ),
        ) {
            Text(text = "BACK")
        }

        //next button
        Button(
            onClick = if (index != 2) {
                onNextClicked
            } else {
                {
//                    onBoardingViewModel.setOnSkipCaseCompleted(false)
                    navController.navigate(ScreenDestinations.HomeScreen.route) {
                        popUpTo(ScreenDestinations.OnBoarding.route) {
                            inclusive = true
                        }
                    }
                }
            },
            modifier = Modifier
                .align(CenterEnd)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8875FF),
            ),
        ) {
            Text(
                text = if (index != 2) {
                    "NEXT"
                } else {
                    "GET STARTED"
                },
                modifier = Modifier.padding(5.dp),
            )
        }

    }

}

@Composable
fun IndicatorSection(
    size: Int,
    index: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        //indicators
        Indicators(size = size, index = index)
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.Center),
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}


@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = "",
    )
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(
                    if (isSelected) Color.White
                    else Color(0XFFAFAFAF),
                ),
        )
    }


}

@Composable
fun OnBoardingItem(
    item: OnBoardingItem,
    size: Int,
    index: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.size(200.dp, 200.dp),
            painter = painterResource(item.image),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.size(20.dp))

        IndicatorSection(size, index)

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            modifier = Modifier.size(281.dp, 38.dp),
            text = stringResource(item.title),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            modifier = Modifier.size(299.dp, 48.dp),
            text = stringResource(item.text),
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
fun TopSection(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {

        //back button
        IconButton(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Icon(Icons.Outlined.KeyboardArrowLeft, null)
        }

        //skip button
        TextButton(
            onClick = {
//                navController?.navigate(ScreenDestinations.StartShowCaseScreen.route) {
//                    popUpTo(ScreenDestinations.HomeScreen.route) {
//                        inclusive = true
//                    }
//                }
            },
            modifier = Modifier.align(CenterEnd),
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = "Skip",
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

    }
}


@Preview
@Composable
private fun BottomScreenPreview() {
    LifeCanvasTheme {
        BottomSection(navController = rememberNavController(), size = 3, index = 1, onBackClicked = { /*TODO*/ }) {

        }
    }

}
