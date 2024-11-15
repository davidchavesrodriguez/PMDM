package com.example.ud07_1_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
fun myApp(modifier: Modifier) {
   Greeting(listOf("World", "Sabela"), modifier)
}

@Composable
fun Greeting(names: List<String>, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.primary) {

        Column (modifier.fillMaxSize()){
            for (name in names)(
                Text(
                    text = "Hello $name",
                    textAlign = TextAlign.Center
                )
            )
            ElevatedButton(onClick = {

            }){
                Text(text = "Show more")
            }
        }

//        Row {
//            Text(
//                text = stringResource(R.string.hello).plus(", ").plus(name),
//                modifier = modifier
//                    .padding(16.dp)
//                    .size(80.dp),
//                style = TextStyle(
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Italic,
//                    color = Color.White,
//                    shadow = Shadow(
//                        color = Color.Gray,
//                        offset = Offset(4.0f, 4.0f),
//                        blurRadius = 6f
//                    )
//                )
//            )
//            Text(
//                text = stringResource(R.string.poem),
//                modifier = modifier.padding(15.dp).size(1000.dp),
//                style = TextStyle(
//                    brush = Brush.linearGradient(listOf(Color.Red, Color.Yellow, Color.Green))
//                ),
//                textAlign = TextAlign.Left
//            )
//        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UD07_1_ComposeTheme {
        myApp(Modifier)
    }
}
