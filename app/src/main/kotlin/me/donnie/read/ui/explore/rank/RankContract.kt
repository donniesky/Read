package me.donnie.read.ui.explore.rank

import me.donnie.read.common.base.BaseNavigator
import me.donnie.read.common.base.BasePresenter
import me.donnie.read.common.base.BaseView
import me.donnie.read.data.entity.RankList

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
interface RankContract {

    interface Navigator : BaseNavigator

    interface View : BaseView {
        fun loadAllRankSuccess(ranks: List<RankList.Rank>)
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun loadAllRank()
        fun onAllRankResponse(ranks: List<RankList.Rank>)
    }

}