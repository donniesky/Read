package me.donnie.read.ui.explore.subject

import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.module.ApiModule
import me.donnie.read.common.injection.scope.ActivityScope
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/18.
 * @description
 * @version
 */
@Module(includes = arrayOf(ApiModule::class))
class SubjectModule @Inject constructor(val activity: SubjectActivity) {

    @ActivityScope
    @Provides
    fun provideSubjectActivity(): SubjectActivity {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideSubjectNavigator(navigator: SubjectNavigator): SubjectContract.Navigator {
        return navigator
    }

    @ActivityScope
    @Provides
    fun provideSubjectPresenter(presenter: SubjectPresenter): SubjectContract.Presenter {
        return presenter
    }

}