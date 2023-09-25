package cash.spont.v6.takeaway.ui.screens.onboardingscreens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cash.spont.v6.takeaway.datastore.DataStoreHelper
import cash.spont.v6.tvtakeaway.R.drawable
import cash.spont.v6.takeaway.ui.theme.appTheme
import cash.spont.v6.takeaway.ui.theme.navigationBarPadding
import cash.spont.v6.takeaway.ui.theme.statusBarPadding

private val data = mutableStateOf("")
private val height = mutableStateOf(0.0f)
private val width = mutableStateOf(0.0f)
private val buttonHeight = mutableStateOf(0.0f)
private val buttonWidth = mutableStateOf(0.0f)


@Composable
fun Auth1View() {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            height.value = 0.8f
            width.value = 0.6f
            buttonHeight.value = 0.14f
            buttonWidth.value = 0.35f
        }

        else -> {
            height.value = 0.6f
            width.value = 0.8f
            buttonHeight.value = 0.11f
            buttonWidth.value = 0.45f
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appTheme {})
            .padding(
                top = MaterialTheme.statusBarPadding(),
                bottom = MaterialTheme.navigationBarPadding()
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight(height.value)
                .fillMaxWidth(width.value),
            shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
            colors = if (isSystemInDarkTheme()) {
                CardDefaults.cardColors(Color(0xFF212127))
            } else {
                CardDefaults.cardColors(Color(0x63BBBBBD))
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    Arrangement.SpaceBetween,
                    Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                    Auth1ViewLogo()
                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                    Auth1ViewDescription()
                }
                Text(text = data.value)
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Auth1ViewImage()
            }
        }
    }
}

@Composable
private fun Auth1ViewImage() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = drawable.authstep1),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Bottom,
            Alignment.CenterHorizontally
        ) {
            Auth1ViewButton()
            Spacer(modifier = Modifier.fillMaxHeight(0.3f))
        }
    }
}

@Composable
private fun Auth1ViewLogo() {
    val icon = if (isSystemInDarkTheme()) {
        drawable.spont_white
    } else {
        drawable.spont_dark
    }
    Icon(
        painter = painterResource(id = icon),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(
                if (isSystemInDarkTheme()) 28.dp else
                    40.dp
            )
            .padding(horizontal = 100.dp)
    )
}

@Composable
private fun Auth1ViewDescription() {
    Text(
        "Welkom,lets activate your new device by scanning your QR code",
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
        lineHeight = 25.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp)
    )
}

@Composable
private fun Auth1ViewButton() {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = DataStoreHelper(context)
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth(buttonWidth.value)
            .fillMaxHeight(buttonHeight.value),
        colors = ButtonDefaults.buttonColors(Color(0xFF4353FF))
    ) {
        Text(
            text = "Scan and Activate", color = Color.White, fontSize = 22.sp,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}