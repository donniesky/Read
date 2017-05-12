package me.donnie.read.ui.community.discuss

import com.bumptech.glide.Glide
import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.BuildConfig
import me.donnie.read.R
import me.donnie.read.common.utils.GlideCircleTransform
import me.donnie.read.common.utils.friendly_time
import me.donnie.read.data.entity.PostList

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class DiscussAdapter : BaseAdapter<PostList.Post> {

    constructor(): super(R.layout.list_discuss_item, null)

    override fun convert(holder: ViewHolder, post: PostList.Post, position: Int) {
        Glide.with(mContext)
                .load(BuildConfig.IMG_BASE_URL + post.author.avatar)
                .transform(GlideCircleTransform(mContext))
                .crossFade()
                .into(holder.getView(R.id.avatar))

        holder.setText(R.id.name, post.author.nickname + "   lv."+ post.author.lv)
        holder.setText(R.id.time, post.updated.friendly_time())
        holder.setText(R.id.desc, post.title)
        holder.setText(R.id.cmt_count, post.commentCount.toString())
        holder.setText(R.id.fav_count, post.likeCount.toString())
    }


}