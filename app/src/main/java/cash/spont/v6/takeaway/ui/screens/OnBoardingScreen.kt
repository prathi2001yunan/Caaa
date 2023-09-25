package cash.spont.v6.takeaway.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cash.spont.v6.takeaway.ui.screens.onboardingscreens.Auth2View
import cash.spont.v6.takeaway.ui.viewmodel.Auth0ViewModel
import cash.spont.v6.takeaway.ui.viewmodel.MyViewModelFactory

@Composable
fun OnBoardingScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val context = LocalContext.current
        val viewModel: Auth0ViewModel = viewModel(factory = MyViewModelFactory(context))
        viewModel.getDeviceCode()
        Auth2View()
    }
}

