package me.donnie.read.ui.explore.rank.fragment

import me.donnie.read.data.entity.BookList
import me.donnie.read.ui.explore.rank.hot.HotRankContract
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/16.
 * @description
 * @version
 */
class HotCateNavigator @Inject constructor(val navigator: HotRankContract.Navigator) : HotCateContract.Navigator {
    override fun navigateToDetail(book: BookList.Book) {
        navigator.navigateToDetail(book)
    }

}