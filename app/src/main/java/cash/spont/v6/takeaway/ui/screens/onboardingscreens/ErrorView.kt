package cash.spont.v6.takeaway.ui.screens.onboardingscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorView(text: String) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Center, Alignment.CenterHorizontally
        ) {
            Text(text = text, fontSize = 35.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                },
                Modifier
                    .width(150.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "back",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )
            }
        }
    }
}