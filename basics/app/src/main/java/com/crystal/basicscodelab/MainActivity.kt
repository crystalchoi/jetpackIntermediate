package com.crystal.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

    var shouldShowOnboarding by remember {
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
                      names: List<String> = listOf("World", "Compose")) {

    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            var isExpaned by remember { mutableStateOf(false) }
            Greeting(name, isExpaned) { isExpaned = !it }
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
fun Greeting(name: String, isExpaned: Boolean, onClickButton: (Boolean)->Unit) {


    val extraPadding = if (isExpaned) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary
        , modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically
            , modifier = Modifier
                .padding(24.dp)
                .padding(bottom = extraPadding)){

            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello")
                Text(text = "$name!")
            }

            ElevatedButton(onClick = { onClickButton(isExpaned) }, modifier = Modifier
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