package me.donnie.read.ui.detail.interest

import com.bumptech.glide.Glide
import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.BuildConfig
import me.donnie.read.R
import me.donnie.read.data.entity.BookList

/**
 * @author donnieSky
 * @created_at 2017/5/24.
 * @description
 * @version
 */
class InterestAdapter : BaseAdapter<BookList.Book> {

    constructor(): super(R.layout.list_inter_view, null)

    override fun convert(holder: ViewHolder, book: BookList.Book, position: Int) {
        Glide.with(mContext).load(BuildConfig.IMG_BASE_URL+book.cover)
                .centerCrop()
                .crossFade()
                .into(holder.getView(R.id.cover))

        holder.setText(R.id.name, book.title)
    }

}