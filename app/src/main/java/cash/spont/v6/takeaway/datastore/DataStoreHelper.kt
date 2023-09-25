package cash.spont.v6.takeaway.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreHelper(private val context: Context) {

    // to make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by
        preferencesDataStore("DataCheck")
        private const val USER_CODE = " USER_CODE"
        private const val VERIFY_URL = "VERIFY_URL"
        private const val COMPLETE_VERIFY_URL = "COMPLETE_VERIFY_URL"
        private const val KEY_ID_ACCESS = "KEY_ID_ACCESS"
        private const val KEY_ID_REFRESH_TOKEN = "KEY_ID_REFRESH_TOKEN"
        private const val KEY_ID_EXPIRES_IN = "KEY_ID_EXPIRES_IN"
        private const val KEY_ID_USER = "KEY_ID_USER"
        private const val KEY_ID_COMPANY = "KEY_ID_COMPANY"
        private const val KEY_ID_DEVICE_UUID = "KEY_ID_DEVICE_UUID"
    }

    private val userCode  = stringPreferencesKey(USER_CODE)
    private val completeUrl  = stringPreferencesKey(COMPLETE_VERIFY_URL)
    private val verifyUrl  = stringPreferencesKey(VERIFY_URL)
    private val keyAccessToken = stringPreferencesKey(KEY_ID_ACCESS)
    private val keyRefreshToken = stringPreferencesKey(KEY_ID_REFRESH_TOKEN)
    private val keyExpireIn = intPreferencesKey(KEY_ID_EXPIRES_IN)
    private val keyDeviceId = stringPreferencesKey(KEY_ID_DEVICE_UUID)


    val getAccessToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[keyAccessToken] ?: ""
        }
    val getUserCode: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[userCode] ?: ""
        }
    val getVerifyUrl: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[verifyUrl] ?: ""
        }
    val getCompleteVerifyUrl: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[completeUrl] ?: ""
        }


    suspend fun updateAccessToken(accessToken: String?) {
        context.dataStore.edit { preferences ->
            preferences[keyAccessToken] = accessToken ?: ""
        }
    }

    val getRefreshToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[keyRefreshToken] ?: ""
    }

    val getCurrentDeviceUuid: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[keyDeviceId] ?: ""
    }
    val getExpiryIn: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[keyExpireIn] ?: 0
    }

    suspend fun updateUserCode(userCode1: String?) {
        context.dataStore.edit { preferences ->
            preferences[userCode] = userCode1 ?: ""
        }
    }

    suspend fun updateVerifyUrl(verifyUrl1: String?) {
        context.dataStore.edit { preferences ->
            preferences[verifyUrl] = verifyUrl1 ?: ""
        }
    }

    suspend fun updateCompleteUrl(completeUrl1: String?) {
        context.dataStore.edit { preferences ->
            preferences[completeUrl] = completeUrl1 ?: ""
        }
    }

    suspend fun updateRefreshToken(refreshToken: String?) {
        context.dataStore.edit { preferences ->
            preferences[keyRefreshToken] = refreshToken ?: ""
        }
    }

    suspend fun updateExpiresIn(expiresIn: Int?) {
        context.dataStore.edit { preferences ->
            preferences[keyExpireIn] = expiresIn ?: 0
        }
    }

    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun updateUUID(uuid: String) {
        context.dataStore.edit { preferences ->
            preferences[keyDeviceId] = uuid ?: ""
        }
    }
}
