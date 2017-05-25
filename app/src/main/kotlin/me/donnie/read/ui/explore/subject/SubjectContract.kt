package me.donnie.read.ui.explore.subject

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView

/**
 * @author donnieSky
 * @created_at 2017/5/18.
 * @description
 * @version
 */
interface SubjectContract {

    interface Navigator : BaseNavigator

    interface View : BaseView

    interface Presenter : BasePresenter<View>

}