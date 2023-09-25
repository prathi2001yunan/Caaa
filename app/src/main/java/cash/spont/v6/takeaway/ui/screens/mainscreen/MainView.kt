package cash.spont.v6.takeaway.ui.screens.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cash.spont.v6.takeaway.manager.SessionManager
import cash.spont.v6.takeaway.ui.theme.navigationBarPadding
import cash.spont.v6.takeaway.ui.theme.statusBarPadding
import cash.spont.v6.takeaway.ui.viewmodel.Auth0ViewModel
import cash.spont.v6.tvtakeaway.R
import kotlinx.coroutines.launch


private var menuClick = mutableStateOf(false)
private var showDialog = mutableStateOf(false)
val openDialog = mutableStateOf(false)
val dropDownWidth = mutableStateOf(0.0f)

@Composable
fun MainView() {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            dropDownWidth.value = 0.1f
        }

        else -> {
            dropDownWidth.value = 0.15f
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) Color.Black
                else Color.White
            )
            .padding(
                top = MaterialTheme.statusBarPadding(),
                bottom = MaterialTheme.navigationBarPadding()
            ),
    ) {
        Preparing()
        Column(Modifier.fillMaxHeight(), Arrangement.Center, Alignment.CenterHorizontally) {
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(3.dp),
                color = if (isSystemInDarkTheme()) Color(0xFF3C3C3C)
                else Color(0xB09E9EA0)
            )
        }
        PleaseCollect()
    }
}

@Composable
private fun Preparing() {
    ItemCheck(
        text = "Preparing",
        0.5f
    )
}

@Composable
private fun PleaseCollect() {
    ItemCheck(text = "Please Collect", 1f)
}

@Composable
private fun ListColumn() {
  val orderListData = mutableListOf<Int>()
    for (i in 0..30) {
        orderListData.add(i)
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        orderListData.forEach { i ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(
                        if (i % 2 == 0) {
                            if (isSystemInDarkTheme()) Color(0x8FBBBBBD)
                            else Color(0x63BBBBBD)
                        } else Color.Transparent
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "123",
                    modifier = Modifier
                        .height(40.dp)
                        .padding(horizontal = 50.dp),
                    fontSize = 25.sp,
                    color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black,
                )
            }
        }
    }
}

@Composable
private fun ItemCheck(
    text: String,
    maxWidth: Float
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(maxWidth),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.01f))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(22.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Text(
                text = text, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold,
                color = if (isSystemInDarkTheme()) Color.White
                else Color.Black,
                modifier = Modifier.padding(start = 30.dp)
            )
            if (text == "Please Collect") {
                Column {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_menu_24),
                        contentDescription = "",
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { menuClick.value = !menuClick.value }
                    )
                    if (text == "Please Collect") {
                        DropDown()
                    }
                }
            } else {
                Spacer(modifier = Modifier.size(40.dp))
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.02f))
        if (showDialog.value) {
            openDialog.value = !openDialog.value
            AlertDialog()
            menuClick.value = false
        }
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            ListColumn()
        }
    }
}

@Composable
fun AlertDialog() {
    val scope = rememberCoroutineScope()
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            modifier = Modifier.fillMaxWidth(1f),
            title = {
                Text(
                    text = "Confirmation!!",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 35.sp
                )
            },
            text = { Text(text = "Confirm to logout ???", fontSize = 25.sp) },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    showDialog.value = false
                    menuClick.value = false
                }) {
                    Text("Confirm", fontSize = 20.sp)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    showDialog.value = false
                    menuClick.value = false
                }) {
                    Text("Dismiss", fontSize = 20.sp)
                }
            }
        )
    }
}

@Composable
fun DropDown() {
    DropdownMenu(
        expanded = menuClick.value,
        onDismissRequest = { menuClick.value = !menuClick.value },
        modifier = Modifier.fillMaxWidth(dropDownWidth.value)
    ) {
        DropdownMenuItem(text = { Text(text = "Settings", fontSize = 23.sp) }, onClick = {})
        DropdownMenuItem(
            text = { Text(text = "Logout", fontSize = 23.sp) },
            onClick = { showDialog.value = !showDialog.value }
        )
    }
}

