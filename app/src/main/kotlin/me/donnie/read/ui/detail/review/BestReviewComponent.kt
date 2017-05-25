package me.donnie.read.ui.detail.review

import dagger.Subcomponent
import me.donnie.read.common.injection.scope.FragmentScope

/**
 * @author donnieSky
 * @created_at 2017/5/23.
 * @description
 * @version
 */
@FragmentScope
@Subcomponent(modules = arrayOf(BestReviewModule::class))
interface BestReviewComponent {

    fun inject(fragment: BestReviewFragment)

}