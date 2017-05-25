package me.donnie.read.ui.main

import android.os.Bundle
import com.github.pwittchen.prefser.library.Prefser
import me.donnie.read.data.entity.BookList
import me.donnie.read.ui.community.discuss.DiscussActivity
import me.donnie.read.ui.detail.BookDetailActivity
import me.donnie.read.ui.explore.rank.RankActivity
import me.donnie.read.ui.explore.subject.SubjectActivity
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class MainNavigator @Inject constructor(val activity: MainActivity) : MainContract.Navigator {

    @Inject
    lateinit var prefser: Prefser

    override fun navigateToRecommend() {

    }

    override fun navigateToDiscussList() {
        val callingIntent = DiscussActivity.getCallingIntent(activity)
        activity.startActivity(callingIntent)
    }

    override fun navigateToRank() {
        val callingIntent = RankActivity.getCallingIntent(activity)
        activity.startActivity(callingIntent)
    }

    override fun navigateToSubject() {
        val callingIntent = SubjectActivity.getCallingIntent(activity)
        activity.startActivity(callingIntent)
    }

    override fun navigateToDetail(book: BookList.Book) {
        val callingIntent = BookDetailActivity.getCallingIntent(activity)
        val args: Bundle = Bundle()
        args.putParcelable(BookDetailActivity.PARAM_BOOK, book)
        callingIntent.putExtras(args)
        activity.startActivity(callingIntent)
    }

}