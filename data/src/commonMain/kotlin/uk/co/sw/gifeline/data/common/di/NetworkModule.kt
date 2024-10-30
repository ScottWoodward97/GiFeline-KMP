package uk.co.sw.gifeline.data.common.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import uk.co.sw.gifeline.data.common.interceptor.ApiKeyInterceptor

internal val networkModule = module {

    single<String>(
        named("CatBaseUrl")
    ) {
        "https://api.thecatapi.com/"
    }

    factory { ApiKeyInterceptor() }

    factory<HttpClient>(
        named("CatKtor")
    ) {
        HttpClient(OkHttp) {
            engine {
                addNetworkInterceptor(get<ApiKeyInterceptor>())
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            defaultRequest {
                url(get<String>(qualifier = named("CatBaseUrl")))
            }
        }
    }

}