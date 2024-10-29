package uk.co.sw.gifeline.data.breed.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uk.co.sw.gifeline.data.breed.CatBreedService
import uk.co.sw.gifeline.data.breed.CatBreedServiceImpl
import uk.co.sw.gifeline.data.common.di.NetworkModule

@InstallIn(ViewModelComponent::class)
@Module(includes = [NetworkModule::class])
abstract class CatBreedModule {

    @Binds
    abstract fun bindsCatBreedService(serviceImpl: CatBreedServiceImpl): CatBreedService

}