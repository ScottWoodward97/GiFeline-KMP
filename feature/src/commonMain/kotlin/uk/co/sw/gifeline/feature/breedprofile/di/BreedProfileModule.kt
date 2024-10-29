package uk.co.sw.gifeline.feature.breedprofile.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uk.co.sw.gifeline.feature.breedprofile.BreedProfileViewModel
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewDataMapper

internal val breedProfileModule = module {

    factory { BreedProfileViewDataMapper() }

    viewModel {
        BreedProfileViewModel(get(), get(), get(), get())
    }
}