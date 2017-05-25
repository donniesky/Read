package me.donnie.read.ui.detail

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.BookDetail

/**
 * @author donnieSky
 * @created_at 2017/5/22.
 * @description
 * @version
 */
interface BookDetailContract {

    interface Navigator : BaseNavigator

    interface View : BaseView {
        fun loadBookDetailSuccess(detail: BookDetail)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadBookDetail(bookId: String)
        fun onBookDetailResponse(detail: BookDetail)
    }

}