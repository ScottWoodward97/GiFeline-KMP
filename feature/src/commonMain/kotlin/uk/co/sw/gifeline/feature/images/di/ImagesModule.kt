package uk.co.sw.gifeline.feature.images.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uk.co.sw.gifeline.feature.images.CatImagesViewModel

internal val imagesModule = module {
    viewModel {
        CatImagesViewModel(get(), get())
    }
}