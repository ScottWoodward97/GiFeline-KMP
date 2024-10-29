package uk.co.sw.gifeline.data.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.co.sw.gifeline.data.common.interceptor.ApiKeyInterceptor

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @CatRetrofit
    @Provides
    fun provideCatRetrofit(
        @CatOkHttp okHttpClient: OkHttpClient,
        @CatBaseUrl baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @CatOkHttp
    @Provides
    fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @CatBaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://api.thecatapi.com/"

    @CatKtor
    @Provides
    fun provideCatHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
        @CatBaseUrl baseUrl: String
    ): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addNetworkInterceptor(apiKeyInterceptor)
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
                url(baseUrl)
            }
        }
    }

}