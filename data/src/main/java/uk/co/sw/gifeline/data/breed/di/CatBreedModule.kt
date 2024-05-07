package uk.co.sw.gifeline.data.breed.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import uk.co.sw.gifeline.data.breed.CatBreedService
import uk.co.sw.gifeline.data.common.di.CatRetrofit
import uk.co.sw.gifeline.data.common.di.NetworkModule

@InstallIn(ViewModelComponent::class)
@Module(includes = [NetworkModule::class])
object CatBreedModule {

    @Provides
    fun providesCatBreedService(@CatRetrofit retrofit: Retrofit): CatBreedService {
        return retrofit.create(CatBreedService::class.java)
    }

}