package me.donnie.read.data.entity

import com.yalantis.filter.model.FilterModel

/**
 * @author donnieSky
 * @created_at 2017/5/19.
 * @description
 * @version
 */
data class Tag(var txt: String,
               var color: Int) : FilterModel {

    override fun getText(): String {
        return txt
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if ((other !is Tag)) return false
        val tag = other
        if (color != tag.color) return false

        return getText() == tag.txt
    }

}