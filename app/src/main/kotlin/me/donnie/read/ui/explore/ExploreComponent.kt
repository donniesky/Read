package me.donnie.read.ui.explore

import dagger.Subcomponent
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(ExploreModule::class))
interface ExploreComponent {

    fun inject(fragment: ExploreFragment)

}