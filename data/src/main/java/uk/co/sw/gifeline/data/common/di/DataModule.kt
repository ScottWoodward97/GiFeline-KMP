package uk.co.sw.gifeline.data.common.di

import org.koin.dsl.module
import uk.co.sw.gifeline.data.breed.di.breedModule
import uk.co.sw.gifeline.data.images.di.imageModule

val dataModule = module {
    includes(breedModule, imageModule)
}