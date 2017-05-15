package me.donnie.read.ui.explore.rank.hot

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.module.ApiModule
import me.donnie.read.common.injection.scope.ActivityScope

/**
 * @author donnieSky
 * @created_at 2017/5/15.
 * @description
 * @version
 */
@Module(includes = arrayOf(ApiModule::class))
class HotRankModule constructor(val activity: HotRankActivity) {

    @ActivityScope
    @Provides
    fun provideHotRankActivity(): HotRankActivity {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideHotRankNavigator(navigator: HotRankNavigator): HotRankContract.Navigator {
        return navigator
    }

    @ActivityScope
    @Provides
    fun provideHotRankPresenter(presenter: HotRankPresenter): HotRankContract.Presenter {
        return presenter
    }

}