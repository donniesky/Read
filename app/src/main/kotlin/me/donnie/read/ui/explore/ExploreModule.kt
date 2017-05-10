package me.donnie.read.ui.explore

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
@Module
class ExploreModule {

    @FragmentScope
    @Provides
    fun provideExploreNavigator(navigator: ExploreNavigator): ExploreContract.Navigator {
        return navigator
    }

}