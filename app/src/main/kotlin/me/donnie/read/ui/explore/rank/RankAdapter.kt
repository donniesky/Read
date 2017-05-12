package me.donnie.read.ui.explore.rank

import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.R
import me.donnie.read.data.entity.RankList

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class RankAdapter : BaseAdapter<RankList.Rank> {

    constructor(): super(R.layout.list_community_item, null)

    override fun convert(holder: ViewHolder, rank: RankList.Rank, position: Int) {
        holder.setImageResource(R.id.icon, R.drawable.ic_assessment)
        holder.setText(R.id.desc, rank.title)
    }

}