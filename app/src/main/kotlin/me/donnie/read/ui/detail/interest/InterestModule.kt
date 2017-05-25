package me.donnie.read.ui.detail.interest

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/24.
 * @description
 * @version
 */
@Module
class InterestModule {

    @FragmentScope
    @Provides
    fun provideInterestNavigator(navigator: InterestNavigator): InterestContract.Navigator {
        return navigator
    }

    @FragmentScope
    @Provides
    fun provideInterestPresenter(presenter: InterestPresenter): InterestContract.Presenter {
        return presenter
    }

}