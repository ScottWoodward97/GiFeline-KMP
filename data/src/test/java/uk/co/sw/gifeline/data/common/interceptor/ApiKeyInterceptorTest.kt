package uk.co.sw.gifeline.data.common.interceptor

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ApiKeyInterceptorTest {

    private lateinit var interceptor: ApiKeyInterceptor

    @Before
    fun setUp() {
        interceptor = ApiKeyInterceptor()
    }

    @Test
    fun `Given request, When intercepted, Then add api key header`() {
        // Given
        val mockRequest: Request = Request.Builder().url("https://url").build()
        val mockResponse: Response = mockk()

        val authRequest = slot<Request>()

        val mockChain: Interceptor.Chain = mockk {
            every { request() } returns mockRequest
            every { proceed(capture(authRequest)) } returns mockResponse
        }

        // When
        val result = interceptor.intercept(mockChain)

        // Then
        assertThat(result).isEqualTo(mockResponse)
        assertThat(authRequest.captured.header("x-api-key"))
            .isEqualTo("live_34ypexfCqJrDVdeCjqV4Zde4UXzS5uexlf1xwISTRKYhRsLtaA7YlT5kv2ChwTmc")
    }

}