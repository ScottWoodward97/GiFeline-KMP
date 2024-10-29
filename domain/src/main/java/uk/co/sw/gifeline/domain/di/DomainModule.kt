package uk.co.sw.gifeline.domain.di

import org.koin.dsl.module
import uk.co.sw.gifeline.domain.breed.di.breedModule
import uk.co.sw.gifeline.domain.images.di.imagesModule

val domainModule = module {
    includes(breedModule, imagesModule)
}