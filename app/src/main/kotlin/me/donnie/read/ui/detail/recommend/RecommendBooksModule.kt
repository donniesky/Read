package me.donnie.read.ui.detail.recommend

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/25.
 * @description
 * @version
 */
@Module
class RecommendBooksModule {

    @FragmentScope
    @Provides
    fun provideRecommendBooksNavigator(navigator: RecommendBooksNavigator): RecommendBooksContract.Navigator {
        return navigator
    }

    @FragmentScope
    @Provides
    fun provideRecommendBooksPresenter(presenter: RecommendBooksPresenter): RecommendBooksContract.Presenter {
        return presenter
    }

}