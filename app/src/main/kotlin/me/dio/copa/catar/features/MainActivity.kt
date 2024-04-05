package me.dio.copa.catar.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import me.dio.copa.catar.ui.theme.Copa2022Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Copa2022Theme {
                val state = viewModel.state.collectAsState()
                MainScreen(matches = state.value.matches)
            }
        }

    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Composable
    @Preview(showBackground = true)
    fun DefaultPreview() {
        Copa2022Theme {
            Greeting("Android")
        }
    }
}
