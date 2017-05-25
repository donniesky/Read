package me.donnie.read.data.entity

import android.os.Parcel
import android.os.Parcelable
import me.donnie.read.common.utils.createParcel
import java.util.*

/**
 * @author donnieSky
 * @created_at 2017/5/23.
 * @description
 * @version
 */
data class BookDetail(var _id: String,
                      var title: String,
                      var author: String,
                      var longIntro: String,
                      var cover: String,
                      var cat: String,
                      var creater: String,
                      var majorCate: String,
                      var minorCate: String,
                      var currency: Int,
                      var contentType: String,
                      var _le: Boolean,
                      var allowMonthly: Boolean,
                      var allowVoucher: Boolean,
                      var allowBeanVoucher: Boolean,
                      var hasCp: Boolean,
                      var postCount: Int,
                      var latelyFollower: Int,
                      var followerCount: Int,
                      var wordCount: Int,
                      var serializeWordCount: Int,
                      var retentionRatio: String,
                      var updated: Date,
                      var isSerial: Boolean,
                      var chaptersCount: Int,
                      var lastChapter: String,
                      var gender: List<String>,
                      var tags: List<String>,
                      var donate: Boolean,
                      var copyright: String) : Parcelable {
    companion object {
        @JvmField val CREATOR = createParcel { BookDetail(it) }
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
            source.readInt(),
            source.readString(),
            1 == source.readInt(),
            1 == source.readInt(),
            1 == source.readInt(),
            1 == source.readInt(),
            1 == source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readSerializable() as Date,
            1 == source.readInt(),
            source.readInt(),
            source.readString(),
            source.createStringArrayList(),
            source.createStringArrayList(),
            1 == source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(_id)
        dest.writeString(title)
        dest.writeString(author)
        dest.writeString(longIntro)
        dest.writeString(cover)
        dest.writeString(cat)
        dest.writeString(creater)
        dest.writeString(majorCate)
        dest.writeString(minorCate)
        dest.writeInt(currency)
        dest.writeString(contentType)
        dest.writeInt((if (_le) 1 else 0))
        dest.writeInt((if (allowMonthly) 1 else 0))
        dest.writeInt((if (allowVoucher) 1 else 0))
        dest.writeInt((if (allowBeanVoucher) 1 else 0))
        dest.writeInt((if (hasCp) 1 else 0))
        dest.writeInt(postCount)
        dest.writeInt(latelyFollower)
        dest.writeInt(followerCount)
        dest.writeInt(wordCount)
        dest.writeInt(serializeWordCount)
        dest.writeString(retentionRatio)
        dest.writeSerializable(updated)
        dest.writeInt((if (isSerial) 1 else 0))
        dest.writeInt(chaptersCount)
        dest.writeString(lastChapter)
        dest.writeStringList(gender)
        dest.writeStringList(tags)
        dest.writeInt((if (donate) 1 else 0))
        dest.writeString(copyright)
    }
}