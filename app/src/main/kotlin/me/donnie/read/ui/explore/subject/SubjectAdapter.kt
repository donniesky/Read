package me.donnie.read.ui.explore.subject

import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import me.donnie.adapter.BaseAdapter
import me.donnie.adapter.ViewHolder
import me.donnie.read.R
import me.donnie.read.data.entity.Question


/**
 * @author donnieSky
 * @created_at 2017/5/19.
 * @description
 * @version
 */
class SubjectAdapter : BaseAdapter<Question> {

    constructor() : super(R.layout.list_subject_item, null)

    override fun convert(holder: ViewHolder, question: Question, position: Int) {
        val avarar = holder.getView<SimpleDraweeView>(R.id.avatar)
        avarar.setImageURI(question.authorAvatar)
        holder.setText(R.id.text_name, question.authorName)
        holder.setText(R.id.text_job_title, question.authorJobTitle)
        holder.setText(R.id.text_date, question.date)
        holder.setText(R.id.text_question, question.text)
        holder.setText(R.id.filter_first, question.tags!![0].getText())
        holder.setText(R.id.filter_second, question.tags!![1].getText())

        val drawable = GradientDrawable()
        drawable.cornerRadius = 1000f
        drawable.setColor(question.tags!![0].color)
        val filter1 = holder.getView<TextView>(R.id.filter_first)
        filter1.setBackgroundDrawable(drawable)

        val drawable1 = GradientDrawable()
        drawable1.cornerRadius = 1000f
        drawable1.setColor(question.tags!![1].color)
        val filter2 = holder.getView<TextView>(R.id.filter_second)
        filter2.setBackgroundDrawable(drawable1)
    }
}