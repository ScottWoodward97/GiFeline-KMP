package uk.co.sw.gifeline

import android.app.Application
import org.koin.android.ext.koin.androidContext
import uk.co.sw.gifeline.di.initKoin

class GiFelineApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@GiFelineApplication)
        }
    }

}