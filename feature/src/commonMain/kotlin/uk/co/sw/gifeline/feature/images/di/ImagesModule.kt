package uk.co.sw.gifeline.feature.images.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uk.co.sw.gifeline.feature.images.CatImagesViewModel

internal val imagesModule = module {

    viewModelOf(::CatImagesViewModel)
}