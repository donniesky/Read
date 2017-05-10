package me.donnie.read.ui.community

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
class CommunityModule {

    @FragmentScope
    @Provides
    fun provideCommunityNavigator(navigator: CommunityNavigator): CommunityContract.Navigator {
        return navigator
    }

}