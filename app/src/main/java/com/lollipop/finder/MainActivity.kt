package com.lollipop.finder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lollipop.finder.ui.theme.ClassFinderTheme
import com.lollipop.mockdemo.LibraryMain

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val contentValue = remember {
                mutableStateOf("Android")
            }
            ClassFinderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Button(
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            LibraryMain.init {
                                val instance = it.getConstructor().newInstance()
                                val info =
                                    "\n class = ${it.name}, ${instance.name()} = ${instance.age()}"
                                contentValue.value += info
                            }
                        }
                    ) {
                        Greeting(
                            name = contentValue.value,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClassFinderTheme {
        Greeting("Android")
    }
}
