package me.donnie.read.ui.main

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.BookList

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
interface MainContract {

    interface Navigator : BaseNavigator {
        fun navigateToRecommend()
        fun navigateToDiscussList()
        fun navigateToRank()
        fun navigateToSubject()
        fun navigateToDetail(book: BookList.Book)
    }

    interface View : BaseView

    interface Presenter : BasePresenter<View>

}