package me.donnie.read.ui.explore.rank

import me.donnie.read.data.entity.RankList
import me.donnie.read.ui.explore.rank.hot.HotRankActivity
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class RankNavigator @Inject constructor(val activity: RankActivity) : RankContract.Navigator {

    override fun navigateToHotRank(rank: RankList.Rank) {
        val callingIntent = HotRankActivity.getCallingIntent(activity)
        callingIntent.putExtra("rank", rank)
        activity.startActivity(callingIntent)
    }

}