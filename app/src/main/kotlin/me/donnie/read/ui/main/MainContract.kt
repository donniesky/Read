package me.donnie.read.ui.main

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
interface MainContract {

    interface Navigator : BaseNavigator {
        fun navigateToRecommend()
    }

    interface View : BaseView

    interface Presenter : BasePresenter<View>

}