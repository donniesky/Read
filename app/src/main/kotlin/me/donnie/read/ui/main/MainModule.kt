package me.donnie.read.ui.main

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.module.ApiModule
import me.donnie.read.common.injection.scope.ActivityScope

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
@Module(includes = arrayOf(ApiModule::class))
class MainModule(val activity: MainActivity) {

    @ActivityScope
    @Provides
    fun provideMainActivity(): MainActivity {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideMainNavigator(navigator: MainNavigator): MainContract.Navigator {
        return navigator
    }

    @ActivityScope
    @Provides
    fun provideMainPresenter(presenter: MainPresenter): MainContract.Presenter {
        return presenter
    }

}