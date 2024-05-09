package uk.co.sw.gifeline.data.common.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authedRequest = request.newBuilder()
            .addHeader(AUTH_HEADER, AUTH_VALUE)
            .build()
        return chain.proceed(authedRequest)
    }

    private companion object {
        const val AUTH_HEADER = "x-api-key"
        const val AUTH_VALUE = "live_34ypexfCqJrDVdeCjqV4Zde4UXzS5uexlf1xwISTRKYhRsLtaA7YlT5kv2ChwTmc"
    }
}