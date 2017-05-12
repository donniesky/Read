package me.donnie.read.ui.community.discuss

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.PostList

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
interface DiscussContract {

    interface Navigator : BaseNavigator

    interface View : BaseView {
        fun loadDiscussListSuccess(discuss: List<PostList.Post>)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadDiscussList(page: Int)
        fun onDiscussListResponse(discuss: List<PostList.Post>)
    }

}