package me.donnie.read.data.entity

import android.os.Parcel
import android.os.Parcelable
import me.donnie.read.common.utils.createParcel
import java.util.*

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class RankList(var female: List<Rank>,
               var male: List<Rank>,
               var ok: Boolean) {

    data class Rank(var _id: String,
                    var updated: Date?,
                    var title: String?,
                    var tag: String?,
                    var cover: String?,
                    var icon: String?,
                    var __v: Int,
                    var collapse: Boolean,
                    var monthRank: String?,
                    var totalRank: String?,
                    var shortTitle: String?,
                    var created: Date?,
                    var isSub: Boolean,
                    var new: Boolean,
                    var gender: String?,
                    var priority: Int,
                    var total: Int,
                    var books: List<BookList.Book>?) : Parcelable {
        companion object {
            @JvmField val CREATOR = createParcel { Rank(it) }
        }

        constructor(source: Parcel) : this(
                source.readString(),
                source.readSerializable() as? Date,
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readInt(),
                1 == source.readInt(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readSerializable() as? Date,
                1 == source.readInt(),
                1 == source.readInt(),
                source.readString(),
                source.readInt(),
                source.readInt(),
                source.createTypedArrayList(BookList.Book.CREATOR)
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(_id)
            dest.writeSerializable(updated)
            dest.writeString(title)
            dest.writeString(tag)
            dest.writeString(cover)
            dest.writeString(icon)
            dest.writeInt(__v)
            dest.writeInt((if (collapse) 1 else 0))
            dest.writeString(monthRank)
            dest.writeString(totalRank)
            dest.writeString(shortTitle)
            dest.writeSerializable(created)
            dest.writeInt((if (isSub) 1 else 0))
            dest.writeInt((if (new) 1 else 0))
            dest.writeString(gender)
            dest.writeInt(priority)
            dest.writeInt(total)
            dest.writeTypedList(books)
        }
    }

}