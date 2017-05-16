package me.donnie.read.ui.explore.rank.hot

import dagger.Component
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.injection.scope.ActivityScope
import me.donnie.read.ui.explore.rank.fragment.HotCateComponent
import me.donnie.read.ui.explore.rank.fragment.HotCateModule

/**
 * @author donnieSky
 * @created_at 2017/5/15.
 * @description
 * @version
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(HotRankModule::class))
interface HotRankComponent {

    fun inject(activity: HotRankActivity)

    fun plus(module: HotCateModule): HotCateComponent

}