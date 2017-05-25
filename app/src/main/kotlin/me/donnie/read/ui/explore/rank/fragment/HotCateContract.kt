package me.donnie.read.ui.explore.rank.fragment

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.BookList

/**
 * @author donnieSky
 * @created_at 2017/5/16.
 * @description
 * @version
 */
interface HotCateContract {

    interface Navigator : BaseNavigator {
        fun navigateToDetail(book: BookList.Book)
    }

    interface View : BaseView {
        fun loadRankSuccess(books: List<BookList.Book>)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadHotRank(id: String)
        fun onHotRankResponse(books: List<BookList.Book>)
    }

}