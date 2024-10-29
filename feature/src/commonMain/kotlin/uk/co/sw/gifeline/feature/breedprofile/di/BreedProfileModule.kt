package uk.co.sw.gifeline.feature.breedprofile.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uk.co.sw.gifeline.feature.breedprofile.BreedProfileViewModel
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewDataMapper
import uk.co.sw.gifeline.feature.di.resourceModule

internal val breedProfileModule = module {

    includes(resourceModule)

    factory { BreedProfileViewDataMapper(get()) }

    viewModel {
        BreedProfileViewModel(get(), get(), get(), get())
    }
}