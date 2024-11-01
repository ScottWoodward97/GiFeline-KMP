package uk.co.sw.gifeline.domain.breed.di

import org.koin.dsl.module
import uk.co.sw.gifeline.domain.breed.CatBreedMapper
import uk.co.sw.gifeline.domain.breed.FindBreedUseCase
import uk.co.sw.gifeline.domain.breed.GetCatBreedsUseCase
import uk.co.sw.gifeline.domain.breed.SearchCatBreedsUseCase

internal val breedModule = module {

    factory { CatBreedMapper() }

    factory { FindBreedUseCase(get(), get()) }

    factory { GetCatBreedsUseCase(get(), get()) }

    factory { SearchCatBreedsUseCase(get(), get()) }
}