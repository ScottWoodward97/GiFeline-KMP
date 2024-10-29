package uk.co.sw.gifeline.feature.di

import android.content.res.Resources
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val resourceModule = module {
     factory<Resources> {
         androidContext().resources
     }
}