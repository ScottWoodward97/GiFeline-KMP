package uk.co.sw.gifeline.data.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}