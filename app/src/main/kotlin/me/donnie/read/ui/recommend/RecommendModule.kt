package me.donnie.read.ui.recommend

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
@Module
class RecommendModule {

    @FragmentScope
    @Provides
    fun provideRecommendNavigator(navigator: RecommendNavigator): RecommendContract.Navigator {
        return navigator
    }

    @FragmentScope
    @Provides
    fun provideRecommendPresenter(presenter: RecommendPresenter): RecommendContract.Presenter {
        return presenter
    }

}