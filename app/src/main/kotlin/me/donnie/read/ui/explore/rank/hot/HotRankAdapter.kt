package me.donnie.read.ui.explore.rank.hot

import android.widget.TextView
import com.bumptech.glide.Glide
import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.BuildConfig
import me.donnie.read.R
import me.donnie.read.common.utils.SpannableBuilder
import me.donnie.read.data.entity.BookList

/**
 * @author donnieSky
 * @created_at 2017/5/15.
 * @description
 * @version
 */
class HotRankAdapter : BaseAdapter<BookList.Book> {

    constructor(): super(R.layout.list_recommend_item, null)

    override fun convert(holder: ViewHolder, book: BookList.Book?, position: Int) {
        Glide.with(mContext).load(BuildConfig.IMG_BASE_URL + book?.cover)
                .crossFade()
                .centerCrop()
                .into(holder.getView(R.id.img))

        val builder = SpannableBuilder.builder(mContext)
        builder.append("作者: ")
                .append(book?.author)

        if (book!!.cat != null) {
            builder.bold("  ||  ")
                    .append("类型: ")
                    .append(book.cat)
        }
        holder.setText(R.id.title, book?.title)
        val author = holder.getView<TextView>(R.id.author)
        author.text = builder
        holder.setText(R.id.desc, book?.shortIntro)

        val dataBuilder = SpannableBuilder.builder(mContext)
        dataBuilder.append(book?.latelyFollower.toString())
                .append("人在追").bold("  ||  ")
                .append(book?.retentionRatio.toString()+"%").append("读者留存")

        val data = holder.getView<TextView>(R.id.update)
        data.text = dataBuilder
    }
}