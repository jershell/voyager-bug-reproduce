package com.github.navtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.SlideTransition
import com.github.navtest.ui.theme.NavTestTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavTestTheme {
                // A surface container using the 'background' color from the theme
                Navigator(ScreenFoo()) { navigator ->
                    Column(Modifier.fillMaxSize()) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .weight(1f)
                        ) {
                            FadeTransition(
                                navigator, animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    visibilityThreshold = null
                                )
                            )
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Button(onClick = { navigator.replace(ScreenFoo()) }) {
                                Text(text = "to FooScreen")
                            }
                            Spacer(Modifier.size(16.dp))
                            Button(onClick = { navigator.replace(ScreenBar()) }) {
                                Text(text = "to BarScreen")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavTestTheme {
        Greeting("Android")
    }
}

class ScreenFoo : Screen {
    @Composable
    override fun Content() {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text("ScreenFoo")
        }
    }
}

class ScreenBar : Screen {
    @Composable
    override fun Content() {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text("ScreenBar")
        }
    }
}