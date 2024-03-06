package com.hamza.learning.dummySamples

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamza.learning.R
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun animatedImage() {

    val image = AnimatedImageVector.animatedVectorResource(id = R.drawable.logo)
    var atEnd by remember { mutableStateOf(false) }
    Image(
        modifier = Modifier.size(height = 150.dp, width = 150.dp),
        painter = rememberAnimatedVectorPainter(animatedImageVector = image, atEnd = atEnd),
        contentDescription = "Logo Image",
        contentScale = ContentScale.FillBounds
    )
    LaunchedEffect(Unit) {
        delay(4000)
        atEnd = true
    }


}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun animatedImagePreview() {
    animatedImage()
}