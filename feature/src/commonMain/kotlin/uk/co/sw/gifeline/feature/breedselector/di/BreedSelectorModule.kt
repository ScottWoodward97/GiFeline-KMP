package uk.co.sw.gifeline.feature.breedselector.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uk.co.sw.gifeline.feature.breedselector.BreedSearchViewModel
import uk.co.sw.gifeline.feature.breedselector.BreedSelectorViewModel
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewDataMapper

internal val breedSelectorModule = module {

    factory { CatBreedViewDataMapper() }

    viewModel {
        BreedSearchViewModel(get(), get())
    }

    viewModel {
        BreedSelectorViewModel(get(), get())
    }
}