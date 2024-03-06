package com.hamza.learning


import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hamza.learning.utils.SharedPreferenceUtil
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController, context: MainActivity) {

    //Todo Add sample animation on the screen for widgets

    val title = listOf(
        "Screen A",
        "Screen B",
        "Screen C"
    )

    val descriptions = listOf(
        "This Is Screen A background(Color.Cyan) background(Color.Cyan)background(Color.Cyan)",
        "This Is Screen B",
        "This Is Screen C"
    )


    val pagerState = rememberPagerState(pageCount = { title.size })



    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            Modifier.wrapContentHeight()
        ) { currentPage ->
            Column(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title[currentPage],
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = descriptions[currentPage],
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    softWrap = true,
                )
            }

        }

        PageIndicator(
            pageCount = title.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(60.dp)
        )
    }

    ButtonsSection(
        pagerState = pagerState,
        navController = navController,
        context = context
    )

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonsSection(pagerState: PagerState, navController: NavHostController, context: MainActivity) {

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)){
        if (pagerState.currentPage != 2){
            Text(text = "Next",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable {

                        scope.launch {
                            val nextPage = pagerState.currentPage +1
                            pagerState.scrollToPage(nextPage)
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(text = "Back",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clickable {
                        scope.launch {
                            val prevPage = pagerState.currentPage -1
                            if (prevPage >= 0){
                                pagerState.scrollToPage(prevPage)
                            }
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }else{
            OutlinedButton(onClick = {

                //onBoardingIsFinished(context = context)
                SharedPreferenceUtil.setBoardingIsFinished()
                navController.popBackStack()
                navController.navigate("LandingPage")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                , colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x25E92F1E)
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

private fun onBoardingIsFinished(context: MainActivity) {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()

}


@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        repeat(pageCount){
            IndicatorSingleDot(isSelected = it == currentPage )
        }


    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

    val width = animateDpAsState(targetValue = if (isSelected) 35.dp else 15.dp, label = "")
    Box(modifier = Modifier
        .padding(2.dp)
        .height(15.dp)
        .width(width.value)
        .clip(CircleShape)
        .background(if (isSelected) Color(0xFFE92F1E) else Color(0x25E92F1E))
    )
}

