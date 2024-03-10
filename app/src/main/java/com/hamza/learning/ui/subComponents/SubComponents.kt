package com.hamza.learning.ui.subComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hamza.learning.utils.Data
import com.hamza.learning.viewModels.TopBarViewModel

@Composable
fun SubComponentsList(mainIndex: Int, topBarViewModel: TopBarViewModel)
{
    LaunchedEffect(Unit) {
        topBarViewModel.setTitle(Data.componentsArray[mainIndex])
    }

    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Here we will get sub components of ${Data.componentsArray[mainIndex]}",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }

}