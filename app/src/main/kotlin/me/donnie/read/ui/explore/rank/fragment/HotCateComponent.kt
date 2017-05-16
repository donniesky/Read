package me.donnie.read.ui.explore.rank.fragment

import dagger.Subcomponent
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/16.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(HotCateModule::class))
interface HotCateComponent {

    fun inject(fragment: HotCateFragment)

}