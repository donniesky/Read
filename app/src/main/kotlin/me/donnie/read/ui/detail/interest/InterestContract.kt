package me.donnie.read.ui.detail.interest

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.BookList

/**
 * @author donnieSky
 * @created_at 2017/5/24.
 * @description
 * @version
 */
interface InterestContract {

    interface Navigator : BaseNavigator

    interface View : BaseView {
        fun loadRecommendBookSuccess(books: List<BookList.Book>)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadRecommendBooks(bookId: String)
        fun onRecommendBooksResponse(books: List<BookList.Book>)
    }

}