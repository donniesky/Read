package me.donnie.read.common.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.donnie.read.common.App
import me.donnie.read.common.injection.qualifier.ApplicationContext
import javax.inject.Singleton

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
@Module
class AppModule(var app: App) {

    @ApplicationContext
    @Singleton
    @Provides
    fun provideContext(): Context {
        return app
    }

}