package me.donnie.read.ui.detail.recommend

import dagger.Subcomponent
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/25.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(RecommendBooksModule::class))
interface RecommendBooksComponent {

    fun inject(fragment: RecommendBooksFragment)

}