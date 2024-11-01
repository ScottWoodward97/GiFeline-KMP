package uk.co.sw.gifeline.data.images.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import uk.co.sw.gifeline.data.common.di.networkModule
import uk.co.sw.gifeline.data.images.CatImageRepository
import uk.co.sw.gifeline.data.images.CatImagesService
import uk.co.sw.gifeline.data.images.CatImagesServiceImpl

internal val imageModule = module {
    includes(networkModule)

    single<CatImagesService> {
        CatImagesServiceImpl(get(named("CatKtor")))
    }

    factory { CatImageRepository(get()) }
}
