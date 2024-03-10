package com.hamza.learning.ui.mianComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hamza.learning.navigation.NavigationItem
import com.hamza.learning.utils.Data
import com.hamza.learning.viewModels.TopBarViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hamza.learning.navigation.NavigationItem
import com.hamza.learning.utils.Data

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainListItem(index: Int, navController: NavController, modifier: Modifier = Modifier)
{
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = {navController.navigate(NavigationItem.SubComponents.route+"/${index}")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
    ){
        Text(
            text = Data.componentsArray[index],
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
}
@Composable
fun MainComponentsList(navController: NavController, topBarViewModel: TopBarViewModel)
{
    LaunchedEffect(Unit) {
        topBarViewModel.setTitle("Components")
    }
fun MainComponentsList(navController: NavController)
{
    LazyColumn {
        items(Data.componentsArray.size){
            MainListItem(index = it, navController = navController)
        }
    }
}