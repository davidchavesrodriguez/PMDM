package com.example.ud07_1_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import com.example.ud07_1_compose.ui.theme.UD07_1_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UD07_1_ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    myApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun welcomeScreen(modifier: Modifier, onContinueClicked: () -> Unit) {
    Surface(color = Color.LightGray) {
        Column(modifier = modifier.fillMaxSize()) {
            Text(text = "Hola")
            ElevatedButton(
                modifier = Modifier.padding(20.dp),
                onClick = onContinueClicked
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}

@Composable
fun myApp(modifier: Modifier) {
    var nextWelcomeScreen = rememberSaveable {
        mutableStateOf(false)
    }
    if (!nextWelcomeScreen.value) {
        welcomeScreen(modifier,
            onContinueClicked = {
                nextWelcomeScreen.value = true
            })
    } else {
        ListItems()
    }
}

@Composable
fun ListItems(names: List<String> = List(100) { "$it" }, modifier: Modifier = Modifier) {
    LazyColumn {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = remember {
        mutableStateOf(false)
    }

    var extraPadding = if (expanded.value) {
        50.dp
    } else {
        0.dp
    }
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(3.dp)
    ) {

        Row {
            Column(
                modifier = Modifier.weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            ElevatedButton(onClick = {
                expanded.value = !expanded.value
            }) {
                if (expanded.value) {
                    Text(text = "Show Less")
                } else {
                    Text(text = "Show More")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UD07_1_ComposeTheme {
        myApp(modifier = Modifier.fillMaxSize())
    }
}
