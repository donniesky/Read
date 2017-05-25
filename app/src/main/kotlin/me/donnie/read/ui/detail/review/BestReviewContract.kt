package me.donnie.read.ui.detail.review

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.ReviewList

/**
 * @author donnieSky
 * @created_at 2017/5/23.
 * @description
 * @version
 */
interface BestReviewContract {

    interface Navigator : BaseNavigator

    interface View : BaseView {
        fun loadBestReviewsSuccess(reviews: ReviewList)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadBestReviews(id: String)
        fun onReviewsResponse(reviews: ReviewList)
    }

}