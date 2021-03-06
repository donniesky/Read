package me.donnie.read.ui.recommend

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.BookList.Book

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
interface RecommendContract {

    interface Navigator : BaseNavigator {
        fun navigateToDetail(book: Book)
    }

    interface View : BaseView {
        fun loadRecommendSuccess(books: List<Book>)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadRecommend(gender: String)
        fun onRecommendResponse(books: List<Book>)
    }

}