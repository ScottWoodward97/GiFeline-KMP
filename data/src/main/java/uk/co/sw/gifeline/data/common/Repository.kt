package uk.co.sw.gifeline.data.common

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