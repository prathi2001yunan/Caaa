package cash.spont.v6.takeaway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cash.spont.v6.takeaway.ui.screens.OnBoardingScreen
import cash.spont.v6.takeaway.ui.theme.TvTakeAwayTheme
import cash.spont.v6.takeaway.ui.viewmodel.Auth0ViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val view by viewModels<Auth0ViewModel>()
            TvTakeAwayTheme {
                // A surface container using the 'background' color from the theme
                OnBoardingScreen()
            }
        }
    }
}
