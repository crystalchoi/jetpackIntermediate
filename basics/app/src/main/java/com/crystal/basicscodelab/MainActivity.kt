package com.crystal.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crystal.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                // A surface container using the 'background' color from the theme
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: ()->Unit) {
    // TODO: This state should be hoisted
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen() {}
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
        }
        else {
            Greetings()
        }
    }
}

@Composable
private fun Greetings(modifier: Modifier = Modifier,
                      names: List<String> = List(1000) { "$it" }) {

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun GreetingsPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}

@Composable
fun Greeting(name: String) {

    var isExpaned by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (isExpaned) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colorScheme.primary
        , modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically
            , modifier = Modifier
                .padding(24.dp)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))){

            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello")
                Text(text = "$name!")
            }

            ElevatedButton(onClick = { isExpaned = !isExpaned }, modifier = Modifier
            ) {
                val textOnButton = if (isExpaned) "Show less" else "show more"
                Text( text = textOnButton, color = MaterialTheme.colorScheme.secondary)
            }

        }
    }
}



@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyAppPreview() {
    BasicsCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    BasicsCodelabTheme {
//        Greeting("Android", false) { }
//    }
//}