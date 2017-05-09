package me.donnie.read.ui.recommend

import android.widget.ImageView
import com.bumptech.glide.Glide
import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.BuildConfig
import me.donnie.read.R
import me.donnie.read.data.entity.Book

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class RecommendAdapter : BaseAdapter<Book> {

    constructor() : super(R.layout.list_recommend_item, null)

    override fun convert(holder: ViewHolder, book: Book, position: Int) {
        holder.setText(R.id.title, book.title)
        holder.setText(R.id.author, book.author)
        holder.setText(R.id.desc, book.shortIntro)

        Glide.with(mContext).load(BuildConfig.IMG_BASE_URL + book.cover)
                .crossFade()
                .centerCrop()
                .into(holder.getView(R.id.img))
    }


}