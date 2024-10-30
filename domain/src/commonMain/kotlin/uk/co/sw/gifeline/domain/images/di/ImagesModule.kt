package uk.co.sw.gifeline.domain.images.di

import org.koin.dsl.module
import uk.co.sw.gifeline.domain.images.CatImageMapper
import uk.co.sw.gifeline.domain.images.GetCatImagesUseCase

internal val imagesModule = module {

    factory { CatImageMapper() }

    factory { GetCatImagesUseCase(get(), get()) }
}