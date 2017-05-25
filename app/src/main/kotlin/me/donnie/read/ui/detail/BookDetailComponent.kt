package me.donnie.read.ui.detail

import dagger.Component
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.injection.scope.ActivityScope
import me.donnie.read.ui.detail.interest.InterestComponent
import me.donnie.read.ui.detail.interest.InterestModule
import me.donnie.read.ui.detail.recommend.RecommendBooksComponent
import me.donnie.read.ui.detail.recommend.RecommendBooksModule
import me.donnie.read.ui.detail.review.BestReviewComponent
import me.donnie.read.ui.detail.review.BestReviewModule

/**
 * @author donnieSky
 * @created_at 2017/5/22.
 * @description
 * @version
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(BookDetailModule::class))
interface BookDetailComponent {

    fun inject(activity: BookDetailActivity)

    fun plus(module: BestReviewModule): BestReviewComponent

    fun plus(module: InterestModule): InterestComponent

    fun plus(module: RecommendBooksModule): RecommendBooksComponent

}