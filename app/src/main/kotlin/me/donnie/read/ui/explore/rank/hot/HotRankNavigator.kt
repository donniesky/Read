package me.donnie.read.ui.explore.rank.hot

import android.os.Bundle
import me.donnie.read.data.entity.BookList
import me.donnie.read.ui.detail.BookDetailActivity
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/15.
 * @description
 * @version
 */
class HotRankNavigator @Inject constructor(val activity: HotRankActivity) : HotRankContract.Navigator {

    override fun navigateToDetail(book: BookList.Book) {
        val callingIntent = BookDetailActivity.getCallingIntent(activity)
        var args: Bundle = Bundle()
        args.putParcelable(BookDetailActivity.PARAM_BOOK, book)
        callingIntent.putExtras(args)
        activity.startActivity(callingIntent)
    }

}