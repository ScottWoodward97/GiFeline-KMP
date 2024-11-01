package uk.co.sw.gifeline.feature.breedprofile.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uk.co.sw.gifeline.feature.breedprofile.BreedProfileViewModel
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewDataMapper
import uk.co.sw.gifeline.feature.common.ResourceStringProvider

internal val breedProfileModule = module {

    factory { ResourceStringProvider() }

    factory { BreedProfileViewDataMapper(get()) }

    viewModelOf(::BreedProfileViewModel)
}