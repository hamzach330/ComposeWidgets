package com.hamza.learning

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.learning.utils.SharedPreferenceUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    private val _isOnboardingFinish = MutableStateFlow(false)
    val isOnboardingFinish = _isOnboardingFinish.asStateFlow()

    init {
        viewModelScope.launch {
            _isOnboardingFinish.value = SharedPreferenceUtil.getBoardingIsFinished()
            delay(1000L)
            _isReady.value = true
        }
    }


    private fun onBoardingIsFinished(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFinished", false)

    }


}