package me.donnie.read.ui.explore

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
interface ExploreContract {

    interface Navigator : BaseNavigator {
        fun navigateToRank()
    }

    interface View : BaseView

    interface Presenter : BasePresenter<View>

}