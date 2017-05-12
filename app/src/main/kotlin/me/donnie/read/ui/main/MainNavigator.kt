package me.donnie.read.ui.main

import com.github.pwittchen.prefser.library.Prefser
import me.donnie.read.ui.community.discuss.DiscussActivity
import me.donnie.read.ui.explore.rank.RankActivity
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

}