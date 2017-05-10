package me.donnie.read.ui.community

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
interface CommunityContract {

    interface Navigator : BaseNavigator

    interface View : BaseView

    interface Presenter : BasePresenter<View>

}