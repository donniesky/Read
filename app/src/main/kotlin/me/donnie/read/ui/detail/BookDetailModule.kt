package me.donnie.read.ui.detail

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.module.ApiModule
import me.donnie.read.common.injection.scope.ActivityScope

/**
 * @author donnieSky
 * @created_at 2017/5/22.
 * @description
 * @version
 */
@Module(includes = arrayOf(ApiModule::class))
class BookDetailModule constructor(val activity: BookDetailActivity) {

    @ActivityScope
    @Provides
    fun provideBookDetailActivity(): BookDetailActivity {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideBookDetailNavigator(navigator: BookDetailNavigator): BookDetailContract.Navigator {
        return navigator
    }

    @ActivityScope
    @Provides
    fun provideBookDetailPresenter(presenter: BookDetailPresenter): BookDetailContract.Presenter {
        return presenter
    }
}