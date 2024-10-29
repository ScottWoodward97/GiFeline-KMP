package uk.co.sw.gifeline.data.breed.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import uk.co.sw.gifeline.data.breed.CatBreedRepository
import uk.co.sw.gifeline.data.breed.CatBreedService
import uk.co.sw.gifeline.data.breed.CatBreedServiceImpl
import uk.co.sw.gifeline.data.common.di.networkModule

internal val breedModule = module {
    includes(networkModule)

    single<CatBreedService> {
        CatBreedServiceImpl(
            get(named("CatKtor"))
        )
    }

    factory { CatBreedRepository(get()) }
}