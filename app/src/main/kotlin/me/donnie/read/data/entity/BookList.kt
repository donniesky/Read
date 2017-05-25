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
class BookList(var books: List<Book>,
               var ok: Boolean) {
    data class Book(var _id: String,
                    var title: String?,
                    var author: String?,
                    var shortIntro: String?,
                    var longIntro: String?,
                    var cover: String?,
                    var cat: String?,
                    var site: String?,
                    var majorCate: String?,
                    var minorCate: String?,
                    var banned: Int,
                    var hasCp: Boolean,
                    var latelyFollower: Int,
                    var retentionRatio: Double,
                    var updated: Date?,
                    var wordCount: Int,
                    var chaptersCount: Int,
                    var lastChapter: String?) : Parcelable {
        companion object {
            @JvmField val CREATOR = createParcel { Book(it) }
        }

        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readInt(),
                1 == source.readInt(),
                source.readInt(),
                source.readDouble(),
                source.readSerializable() as? Date,
                source.readInt(),
                source.readInt(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(_id)
            dest.writeString(title)
            dest.writeString(author)
            dest.writeString(shortIntro)
            dest.writeString(longIntro)
            dest.writeString(cover)
            dest.writeString(cat)
            dest.writeString(site)
            dest.writeString(majorCate)
            dest.writeString(minorCate)
            dest.writeInt(banned)
            dest.writeInt((if (hasCp) 1 else 0))
            dest.writeInt(latelyFollower)
            dest.writeDouble(retentionRatio)
            dest.writeSerializable(updated)
            dest.writeInt(wordCount)
            dest.writeInt(chaptersCount)
            dest.writeString(lastChapter)
        }
    }
}