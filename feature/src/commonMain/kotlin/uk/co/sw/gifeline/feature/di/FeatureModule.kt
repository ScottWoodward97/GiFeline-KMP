package uk.co.sw.gifeline.feature.di

import org.koin.dsl.module
import uk.co.sw.gifeline.feature.breedprofile.di.breedProfileModule
import uk.co.sw.gifeline.feature.breedselector.di.breedSelectorModule
import uk.co.sw.gifeline.feature.images.di.imagesModule

val featureModule = module {
    includes(breedProfileModule, breedSelectorModule, imagesModule)
}