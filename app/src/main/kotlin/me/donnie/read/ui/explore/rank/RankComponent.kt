package me.donnie.read.ui.explore.rank

import dagger.Component
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.injection.scope.ActivityScope

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(RankModule::class))
interface RankComponent {

    fun inject(activity: RankActivity)

}