package me.donnie.read.ui.recommend

import dagger.Subcomponent
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(RecommendModule::class))
interface RecommendComponent {

    fun inject(fragment: RecommendFragment)

}