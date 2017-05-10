package me.donnie.read.ui.community

import dagger.Subcomponent
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(CommunityModule::class))
interface CommunityComponent {

    fun inject(fragment: CommunityFragment)

}