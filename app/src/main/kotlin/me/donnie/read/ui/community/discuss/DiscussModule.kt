package me.donnie.read.ui.community.discuss

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.module.ApiModule
import me.donnie.read.common.injection.scope.ActivityScope

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
@Module(includes = arrayOf(ApiModule::class))
class DiscussModule(val activity: DiscussActivity) {

    @ActivityScope
    @Provides
    fun provideDiscussActivity(): DiscussActivity {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideDiscussNavigator(navigator: DiscussNavigator): DiscussContract.Navigator {
        return navigator
    }

    @ActivityScope
    @Provides
    fun provideDiscussPresenter(presenter: DiscussPresenter): DiscussContract.Presenter {
        return presenter
    }

}