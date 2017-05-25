package me.donnie.read.ui.detail.review

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/23.
 * @description
 * @version
 */
@Module
class BestReviewModule {

    @FragmentScope
    @Provides
    fun provideBestReviewNavigator(navigator: BestReviewNavigator): BestReviewContract.Navigator {
        return navigator
    }

    @FragmentScope
    @Provides
    fun provideBestReviewPresenter(presenter: BestReviewPresenter): BestReviewContract.Presenter {
        return presenter
    }

}