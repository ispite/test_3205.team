package ru.skillbox.test_3205team.app

import android.app.Application
import ru.skillbox.test_3205team.BuildConfig
import timber.log.Timber

class Test3205App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
