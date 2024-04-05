package me.dio.copa.catar.features

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import me.dio.copa.catar.extensions.observe
import me.dio.copa.catar.notification.scheduler.extensions.NotificationMatchesWorker
import me.dio.copa.catar.ui.theme.Copa2022Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observerActions()
        setContent {
            Copa2022Theme {
                val state = viewModel.state.collectAsState()
                MainScreen(matches = state.value.matches,viewModel::toggleNotification)
            }
        }

    }

    private fun observerActions() {
        viewModel.action.observe(this){ action ->
            when(action){
                is MainUiAction.DisableNotification -> NotificationMatchesWorker.cancel(applicationContext, action.match)
                is MainUiAction.EnableNotification -> NotificationMatchesWorker.start(applicationContext, action.match)
                is MainUiAction.MatchesNotFound ->  Toast.makeText(this, action.message, Toast.LENGTH_SHORT).show()
                MainUiAction.UnexpectedError -> Toast.makeText(this, "Erro inesperado", Toast.LENGTH_SHORT).show()
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
