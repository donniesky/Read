package me.donnie.read.ui.main

import dagger.Component
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.injection.scope.ActivityScope
import me.donnie.read.ui.community.CommunityComponent
import me.donnie.read.ui.community.CommunityModule
import me.donnie.read.ui.explore.ExploreComponent
import me.donnie.read.ui.explore.ExploreModule
import me.donnie.read.ui.recommend.RecommendComponent
import me.donnie.read.ui.recommend.RecommendModule

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(activity: MainActivity)

    fun plus(module: RecommendModule): RecommendComponent

    fun plus(module: CommunityModule): CommunityComponent

    fun plus(module: ExploreModule): ExploreComponent

}