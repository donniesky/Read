package me.donnie.read.common

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import me.donnie.read.BuildConfig
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.injection.component.DaggerAppComponent
import me.donnie.read.common.injection.module.AppModule
import timber.log.Timber

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
class App : Application() {

    var appComponent: AppComponent? = null

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initInjector()
    }

    private fun initInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}