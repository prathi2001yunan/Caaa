package cash.spont.v6.takeaway.auth0

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.Parameters

class Auth0RepositoryImpl(val client: HttpClient) : Auth0Repository {
    override suspend fun getDeviceCode(): AuthCodeResponse {
        val response = client.post<AuthCodeResponse> {
            url("https://spont-staging.eu.auth0.com/oauth/device/code")
            body = FormDataContent(
                Parameters.build {
                    append("client_id", "PhNt5IztNApIoGfhlNNfMGbcwm3QIIVe")
                    append("audience", "https://auth0-jwt-authorizer")
                    append("scope", "profile email offline_access")
                }
            )
        }
        val responseData: AuthCodeResponse = response
        print(responseData)
        client.close()
        return responseData
    }


    override suspend fun getDeviceToken(deviceCode: String): AuthTokenResponse {
        val response = client.post<AuthTokenResponse> {
            url("https://spont-staging.eu.auth0.com/oauth/token")
            body = FormDataContent(
                Parameters.build {
                    append("client_id", "PhNt5IztNApIoGfhlNNfMGbcwm3QIIVe")
                    append(
                        "grant_type", "urn:ietf:params:oauth:grant-type:device_code"
                    )
                    append("device_code", deviceCode)
                }
            )
        }
        val responseToken: AuthTokenResponse = response
        print(responseToken)
        client.close()
        return responseToken
    }

    override suspend fun getNewToken(refreshToken: String): AuthInfo {
        val response = client.post<AuthInfo> {
            url("https://spont-staging.eu.auth0.com/oauth/token")
            body = FormDataContent(
                Parameters.build {
                    append("client_id", "PhNt5IztNApIoGfhlNNfMGbcwm3QIIVe")
                    append("grant_type", "refresh_token")
                    append(
                        "client_secret",
                        "sAwr-01ZUp3uawuj-c-KKRD0SUPmQWfswDtHchmGQ146xFAHOcZxd90_8j7hNOWj"
                    )
                    append("refresh_token", refreshToken)
                }
            )
        }
        val responseToken: AuthInfo = response
        print(responseToken)
        client.close()
        return responseToken
    }
}

