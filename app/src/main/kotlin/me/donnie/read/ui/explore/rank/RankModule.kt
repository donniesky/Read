package me.donnie.read.ui.explore.rank

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
class RankModule(val activity: RankActivity) {

    @ActivityScope
    @Provides
    fun provideRankActivity(): RankActivity {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideRankNavigator(navigator: RankNavigator): RankContract.Navigator {
        return navigator
    }

    @ActivityScope
    @Provides
    fun provideRankPresenter(presenter: RankPresenter): RankContract.Presenter {
        return presenter
    }

}