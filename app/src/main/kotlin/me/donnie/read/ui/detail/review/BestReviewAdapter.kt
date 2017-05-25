package me.donnie.read.ui.detail.review

import com.bumptech.glide.Glide
import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.BuildConfig
import me.donnie.read.R
import me.donnie.read.common.utils.GlideCircleTransform
import me.donnie.read.data.entity.ReviewList

/**
 * @author donnieSky
 * @created_at 2017/5/23.
 * @description
 * @version
 */
class BestReviewAdapter : BaseAdapter<ReviewList.Review> {

    constructor(): super(R.layout.list_review_item, null)

    override fun convert(holder: ViewHolder, review: ReviewList.Review, position: Int) {
        Glide.with(mContext).load(BuildConfig.IMG_BASE_URL+review.author.avatar)
                .transform(GlideCircleTransform(mContext))
                .centerCrop()
                .into(holder.getView(R.id.avatar))
        holder.setText(R.id.username, review.author.nickname+"   LV."+review.author.lv)
        holder.setText(R.id.title, review.title)
        holder.setText(R.id.desc, review.content)
        holder.setText(R.id.count, review.commentCount.toString())
    }

}