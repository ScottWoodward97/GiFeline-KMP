package uk.co.sw.gifeline.data.images.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uk.co.sw.gifeline.data.common.di.NetworkModule
import uk.co.sw.gifeline.data.images.CatImagesService
import uk.co.sw.gifeline.data.images.CatImagesServiceImpl

@InstallIn(ViewModelComponent::class)
@Module(includes = [NetworkModule::class])
abstract class CatImageModule {

    @Binds
    abstract fun bindsCatImageService(impl: CatImagesServiceImpl): CatImagesService

}