package uk.co.sw.gifeline

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uk.co.sw.gifeline.data.common.di.dataModule
import uk.co.sw.gifeline.domain.di.domainModule
import uk.co.sw.gifeline.feature.di.featureModule

class GiFelineApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GiFelineApplication)
            modules(dataModule, domainModule, featureModule)
        }
    }

}