package uk.co.sw.gifeline.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import uk.co.sw.gifeline.data.common.di.dataModule
import uk.co.sw.gifeline.domain.di.domainModule
import uk.co.sw.gifeline.feature.di.featureModule

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            featureModule,
            domainModule,
            dataModule,
        )
    }
}