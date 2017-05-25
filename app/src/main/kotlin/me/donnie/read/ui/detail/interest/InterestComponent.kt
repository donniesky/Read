package me.donnie.read.ui.detail.interest

import dagger.Subcomponent
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/24.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(InterestModule::class))
interface InterestComponent {

    fun inject(fragment: InterestFragment)

}