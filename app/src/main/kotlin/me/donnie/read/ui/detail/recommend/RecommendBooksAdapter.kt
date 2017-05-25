package me.donnie.read.ui.detail.recommend

import com.bumptech.glide.Glide
import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.BuildConfig
import me.donnie.read.R
import me.donnie.read.data.entity.BooksList

/**
 * @author donnieSky
 * @created_at 2017/5/25.
 * @description
 * @version
 */
class RecommendBooksAdapter : BaseAdapter<BooksList.Books> {

    constructor(): super(R.layout.list_recommend_books_item, null)

    override fun convert(holder: ViewHolder, books: BooksList.Books, position: Int) {
        Glide.with(mContext).load(BuildConfig.IMG_BASE_URL+books.cover)
                .centerCrop()
                .crossFade()
                .into(holder.getView(R.id.cover))

        holder.setText(R.id.title, books.title)
        holder.setText(R.id.author, books.author)
        holder.setText(R.id.desc, books.desc)
        holder.setText(R.id.count, "共"+books.bookCount+"本书"+"  |  "+books.collectorCount+"人收藏")
    }

}