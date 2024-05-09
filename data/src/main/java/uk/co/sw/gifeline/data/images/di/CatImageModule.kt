package uk.co.sw.gifeline.data.images.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import uk.co.sw.gifeline.data.common.di.CatRetrofit
import uk.co.sw.gifeline.data.common.di.NetworkModule
import uk.co.sw.gifeline.data.images.CatImagesService

@InstallIn(ViewModelComponent::class)
@Module(includes = [NetworkModule::class])
object CatImageModule {

    @Provides
    fun providesCatImageService(@CatRetrofit retrofit: Retrofit): CatImagesService {
        return retrofit.create(CatImagesService::class.java)
    }

}