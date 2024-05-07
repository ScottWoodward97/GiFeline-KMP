package uk.co.sw.gifeline.data.common.response

sealed class ApiResponse<T> {
    class Success<T>(val data: T) : ApiResponse<T>()
    class Error<T>(val error: Throwable) : ApiResponse<T>()
}