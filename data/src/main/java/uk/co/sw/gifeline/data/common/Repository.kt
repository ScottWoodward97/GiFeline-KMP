package uk.co.sw.gifeline.data.common

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import retrofit2.HttpException
import retrofit2.Response
import uk.co.sw.gifeline.data.common.response.ApiResponse

abstract class Repository  {

    suspend fun <T> handleResponse(runBlock: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = runBlock()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ApiResponse.Success(body)
            } else {
                ApiResponse.Error(HttpException(response))
            }
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }
}

abstract class KtorRepository  {

    suspend inline fun <reified T> handleResponse(runBlock: () -> HttpResponse): ApiResponse<T> {
        return try {
            val response = runBlock()
            val body: T = response.body()
            if (response.status.isSuccess()) {
                ApiResponse.Success(body)
            } else {
                ApiResponse.Error(ResponseException(response, response.bodyAsText()))
            }
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }
}