package cash.spont.v6.takeaway.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cash.spont.v6.takeaway.manager.SessionManager
import cash.spont.v6.takeaway.auth0.Auth0Repository
import cash.spont.v6.takeaway.datastore.DataStoreHelper
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


class Auth0ViewModel(context: Context) :
    ViewModel() {
    val service = Auth0Repository.create()
    private val session = SessionManager(dataStore = DataStoreHelper(context))
    var isPollingContinue = mutableStateOf(true)

    fun getDeviceCode() {
        viewModelScope.launch {
            val deviceCodeData = service.getDeviceCode()
            startPollingExpiryTimer(deviceCodeData.expires_in)
            if (deviceCodeData.device_code!!.isNotEmpty()) {
                session.logout()
                session.setDeviceData(
                    deviceCodeData.user_code.toString(),
                    deviceCodeData.verification_uri.toString(),
                    deviceCodeData.verification_uri_complete.toString()
                )
                startPolling(deviceCodeData.device_code)
            }
        }
    }

    fun startPolling(deviceCode: String) {
        if (isPollingContinue.value && deviceCode.isNotEmpty()) {
            viewModelScope.launch {
                val deviceTokenData = service.getDeviceToken(deviceCode)
                if (deviceTokenData.access_token.isNotEmpty()) {
                    AccessTokenExpiry(deviceTokenData.expires_in, deviceTokenData.refresh_token)
                    session.setAuth(
                        deviceTokenData.access_token,
                        deviceTokenData.refresh_token,
                        deviceTokenData.expires_in
                    )
                }

            }
        }
    }

    private fun startPollingExpiryTimer(int: Int) {
        val expirationTimer = Timer()
        expirationTimer.schedule(
            object : TimerTask() {
                override fun run() {
                    isPollingContinue.value = false
                    expirationTimer.cancel()
                    viewModelScope.launch {
                    }
                }
            },
            int * 100L

        )
    }

    private fun AccessTokenExpiry(int: Int, refresh_token: String) {
        val expirationTimer = Timer()
        expirationTimer.schedule(
            object : TimerTask() {
                override fun run() {
                    expirationTimer.cancel()
                    viewModelScope.launch {
                        val newDeviceTokenData = service.getNewToken(refresh_token)
                    }
                }
            },
            int * 100L
        )
    }
}

