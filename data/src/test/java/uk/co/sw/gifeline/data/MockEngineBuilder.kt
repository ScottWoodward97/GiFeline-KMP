package uk.co.sw.gifeline.data

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.engine.mock.respondOk
import io.ktor.http.Parameters
import org.junit.Assert.fail

object MockEngineBuilder {
    fun createMockEngine(expectedPath: String, response: String): MockEngine {
        return MockEngine { request ->
            if (request.url.encodedPath == expectedPath) {
                respondOk(response)
            } else {
                fail("Incorrect request path called: ${request.url.encodedPath}\n" +
                        "Expected: $expectedPath")
                respondBadRequest()
            }
        }
    }

    fun createMockEngine(
        expectedPath: String,
        expectedParameters: Parameters,
        response: String
    ): MockEngine {
        return MockEngine { request ->
            if (request.url.encodedPath == expectedPath && request.url.parameters.entries()
                    .containsAll(expectedParameters.entries())
            ) {
                respondOk(response)
            } else {
                fail("Incorrect request path called: ${request.url.encodedPath}\n" +
                        "With parameters: ${request.url.parameters}\n" +
                        "Expected: $expectedPath\n" +
                        "With parameters: $expectedParameters")
                respondBadRequest()
            }
        }
    }
}