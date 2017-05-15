package me.donnie.read.common.utils

import android.content.Context
import android.graphics.Typeface.BOLD
import android.support.annotation.ColorRes
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.view.View

/**
 * @author donnieSky
 * @created_at 2017/5/15.
 * @description
 * @version
 */
class SpannableBuilder : SpannableStringBuilder() {

    companion object {
        @JvmStatic fun builder(context: Context): SpannableBuilder {
            return SpannableBuilder()
        }
    }

    override fun append(text: Char): SpannableBuilder {
        if (text.toInt() != 0) super.append(text)
        return this
    }

    override fun append(text: CharSequence): SpannableBuilder {
        if (!text.isNullOrEmpty()) super.append(text)
        return this
    }

    fun append(text: CharSequence?, span: Any?): SpannableBuilder {
        if (!text.isNullOrEmpty()) {
            append(text)
            if (span != null) {
                setSpan(span, length - text!!.length, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        return this
    }

    fun append(text: Char, span: Any?): SpannableBuilder {
        append(text)
        if (!span.toString().isNullOrEmpty()) {
            setSpan(span, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return this
    }

    fun bold(text: CharSequence?): SpannableBuilder {
        if (!text.isNullOrEmpty()) {
            return append(text, StyleSpan(BOLD))
        }
        return this
    }

    fun background(text: CharSequence?, @ColorRes color: Int): SpannableBuilder {
        if (!text.isNullOrEmpty()) {
            return append(text, BackgroundColorSpan(color))
        }
        return this
    }

    fun foreground(text: CharSequence?, @ColorRes color: Int): SpannableBuilder {
        if (!text.isNullOrEmpty()) {
            append(text, ForegroundColorSpan(color))
        }
        return this
    }

    fun foreground(text: Char, @ColorRes color: Int): SpannableBuilder {
        return append(text, ForegroundColorSpan(color))
    }

    fun url(text: CharSequence?, listener: View.OnClickListener): SpannableBuilder {
        if (!text.isNullOrEmpty()) {
            return append(text, object : URLSpan(text.toString()) {
                override fun onClick(view: View) {
                    listener.onClick(view)
                }
            })
        }
        return this
    }

    fun url(text: CharSequence?): SpannableBuilder {
        if (!text.isNullOrEmpty()) return append(text, URLSpan(text.toString()))
        return this
    }
}