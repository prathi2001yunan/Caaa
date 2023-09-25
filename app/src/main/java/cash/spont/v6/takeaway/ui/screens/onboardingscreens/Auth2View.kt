package cash.spont.v6.takeaway.ui.screens.onboardingscreens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cash.spont.v6.takeaway.datastore.DataStoreHelper
import cash.spont.v6.takeaway.ui.theme.appTheme
import cash.spont.v6.takeaway.ui.theme.navigationBarPadding
import cash.spont.v6.takeaway.ui.theme.statusBarPadding
import cash.spont.v6.terminal.ui.qr.QRCode
import cash.spont.v6.terminal.ui.qr.QrCodeProperties

private val height = mutableStateOf(0.0f)
private val width = mutableStateOf(0.0f)

@Composable
fun Auth2View() {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            height.value = 0.8f
            width.value = 0.6f
        }

        else -> {
            height.value = 0.6f
            width.value = 0.9f
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
                    .fillMaxSize(1f)
                    .padding(vertical = 20.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterVertically) {
                    Text(
                        text = "Activate",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Spacer(modifier = Modifier.fillMaxSize(0.05f))
                Auth2ViewData()
            }
        }
    }
}

@Composable
private fun Auth2ViewScan() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.4f),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "Scan the device and activate the code.",
                fontSize = 30.sp,
                lineHeight = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.18f))
            Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                        .background(
                            if (isSystemInDarkTheme()) {
                                Color.White
                            } else {
                                Color.White
                            }
                        )
                ) {
                    Column(
                        Modifier.fillMaxSize(),
                        Arrangement.Center,
                        Alignment.CenterHorizontally
                    ) {
                        QRCode(
                            contents = DataStoreHelper(context = LocalContext.current)
                                .getCompleteVerifyUrl.toString(),
                            modifier = Modifier
                                .size(200.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color.White)
                                .padding(8.dp),
                            qrCodeProperties = QrCodeProperties(
                                foreground = Color.Black,
                                background = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun Auth2ViewData() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp),
        Arrangement.Center, Alignment.CenterVertically
    ) {
        Auth2ViewScan()
        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        Column(
            Modifier,
            Arrangement.Top,
            Alignment.CenterHorizontally
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .width(1.dp),
                color = if (isSystemInDarkTheme()) Color(0xFF353539)
                else Color(0xB09E9EA0)
            )
            Text(
                text = "Or",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight(),
                color = if (isSystemInDarkTheme()) Color(0xFF353539)
                else Color(0xB09E9EA0)
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.15f))
        Auth2ViewNoScan()
    }
}

@Composable
private fun Auth2ViewNoScan() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        Column(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text(
                "Not able to scan code?",
                fontSize = 30.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Auth2ViewWebsiteClick()
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Row(modifier = Modifier.fillMaxWidth()) {
            Divider(
                modifier = Modifier.weight(0.4f),
                color = if (isSystemInDarkTheme()) Color(0xFF353539)
                else Color(0xB09E9EA0)
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Auth2ViewUserCode()
    }
}

@Composable
private fun Auth2ViewWebsiteClick() {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        Arrangement.Center
    ) {
        Text(
            text = "Go to this website:", fontSize = 25.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                uriHandler.openUri(DataStoreHelper(context).getCompleteVerifyUrl.toString())
            },
            modifier = Modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF4454FF)
            )
        ) {
            Text(text = "Click here", color = Color.White)
        }
    }
}

@Composable
private fun Auth2ViewUserCode() {
    Column(modifier = Modifier.fillMaxWidth()) {
        val uriHandler = LocalUriHandler.current
        val context = LocalContext.current
        Text(
            text = "Or visit:",
            fontSize = 25.sp, color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = DataStoreHelper(context).getVerifyUrl.toString(),
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.clickable {
                uriHandler.openUri(
                    DataStoreHelper(context).getVerifyUrl.toString()
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Auth2Text(
            text = "User code:",
            text1 = DataStoreHelper(context).getUserCode.toString()
        )
    }
}

@Composable
private fun Auth2Text(text: String, text1: String) {
    Text(text = text, fontSize = 25.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
    Text(text = text1, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
}
