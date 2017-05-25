package me.donnie.read.ui.detail.recommend

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.BooksList

/**
 * @author donnieSky
 * @created_at 2017/5/25.
 * @description
 * @version
 */
interface RecommendBooksContract {

    interface Navigator : BaseNavigator

    interface View : BaseView {
        fun loadRecommendBooksSuccess(books: List<BooksList.Books>)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadRecommendBooks(bookId: String)
        fun onBooksResponse(books: List<BooksList.Books>)
    }

}