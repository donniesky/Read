package me.donnie.read.ui.community

import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.R
import me.donnie.read.data.entity.Community

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
class CommunityAdapter : BaseAdapter<Community> {

    constructor(): super(R.layout.list_community_item, null)

    override fun convert(holder: ViewHolder, community: Community, position: Int) {
        holder.setText(R.id.desc, community.title)
        holder.setImageDrawable(R.id.icon, community.icon)
    }


}