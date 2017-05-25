package me.donnie.read.ui.explore.subject

import dagger.Component
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.injection.scope.ActivityScope

/**
 * @author donnieSky
 * @created_at 2017/5/18.
 * @description
 * @version
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(SubjectModule::class))
interface SubjectComponent {

    fun inject(activity: SubjectActivity)

}