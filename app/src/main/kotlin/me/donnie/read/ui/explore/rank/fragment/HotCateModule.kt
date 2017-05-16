package me.donnie.read.ui.explore.rank.fragment

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/16.
 * @description
 * @version
 */
@Module
class HotCateModule {

    @FragmentScope
    @Provides
    fun provideHotCateNavigator(navigator: HotCateNavigator): HotCateContract.Navigator {
        return navigator
    }

    @FragmentScope
    @Provides
    fun provideHotCatePresenter(presenter: HotCatePresenter): HotCateContract.Presenter {
        return presenter
    }

}