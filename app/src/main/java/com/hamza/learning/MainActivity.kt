package com.hamza.learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hamza.learning.ui.theme.ComposeWidgetsTheme
import com.hamza.learning.utils.SharedPreferenceUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {



    private var startDestination = ""
    private val splashViewModel by viewModels<SplashViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //MainActivitySingleton.setMainActivityInstance(this@MainActivity)
        SharedPreferenceUtil.init(this@MainActivity)


        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isReady.value
        }

        //get start destination check splashviewmodel for the it

        CoroutineScope(Dispatchers.Main).launch {
            // Perform asynchronous operations here
            if (splashViewModel.isOnboardingFinish.value)
                startDestination = "LandingPage"
            else
                startDestination = "Onboarding"
            // Update UI or perform other operations on the main thread


            setContent {
                val nav = rememberNavController()
                ComposeWidgetsTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(), color = Color.White
                    ) {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController, startDestination = startDestination
                        ) {

                            composable("Onboarding") {
                                OnBoardingScreen(
                                    navController = navController,
                                    context = this@MainActivity
                                )
                            }

                            composable("LandingPage") {
                                LandingPage()
                            }

                        }

                    }
                }
            }
        }
    }


}









