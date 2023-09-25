package cash.spont.v6.takeaway.manager

import android.content.Context
import cash.spont.v6.takeaway.datastore.DataStoreHelper

class SessionManager(val dataStore: DataStoreHelper) {

    suspend fun setAuth(accessToken: String, refreshToken: String, expiryInt: Int) {
        dataStore.updateAccessToken(accessToken)
        dataStore.updateRefreshToken(refreshToken)
        dataStore.updateExpiresIn(expiryInt)
    }

    suspend fun setDeviceData(userCode: String, verifyUrl: String, completeVerifyUrl: String) {
        dataStore.updateUserCode(userCode)
        dataStore.updateCompleteUrl(completeVerifyUrl)
        dataStore.updateVerifyUrl(verifyUrl)
    }

    suspend fun getAccessToken(): String? {
        return dataStore.getAccessToken.toString() ?: ""
    }

    suspend fun getRenewToken(): String? {
        return dataStore.getRefreshToken.toString() ?: ""
    }

    suspend fun getCurrentDeviceUuid(): String? {
        return dataStore.getCurrentDeviceUuid.toString() ?: ""
    }

    suspend fun setDevice(device: String) {
        return dataStore.updateUUID(device)
    }

    suspend fun logout() {
        return dataStore.clearData()
    }

}
