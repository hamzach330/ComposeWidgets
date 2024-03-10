package com.hamza.learning.ui.landingPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.hamza.learning.navigation.ComponentsNavHost
import com.hamza.learning.viewModels.TopBarViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage() {
    val navController = rememberNavController()
    val topBarViewModel:TopBarViewModel = viewModel()
    val title: String by topBarViewModel.screenTitle.observeAsState("")
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(title)
                }
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
//            MainComponentsList()
            ComponentsNavHost(navController = navController,topBarModel = topBarViewModel)

        }
    }

}


@Composable
@Preview
fun landingPagePrev()
{
    LandingPage()
}