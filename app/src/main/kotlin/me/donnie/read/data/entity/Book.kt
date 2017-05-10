package me.donnie.read.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import me.donnie.read.common.utils.createParcel
import java.util.*

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
data class Book(var _id: String,
                var title: String,
                var author: String,
                @SerializedName("shortIntro") var shortIntro: String,
                var cover: String,
                var hasCp: Boolean,
                var latelyFollower: Int,
                var retentionRatio: Double,
                var updated: Date,
                var chaptersCount: Int,
                var lastChapter: String) : Parcelable {

    companion object {
        val CREATOR = createParcel { Book(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readInt(),
            source.readDouble(),
            source.readSerializable() as Date,
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(_id)
        dest.writeString(title)
        dest.writeString(author)
        dest.writeString(shortIntro)
        dest.writeString(cover)
        dest.writeInt((if (hasCp) 1 else 0))
        dest.writeInt(latelyFollower)
        dest.writeDouble(retentionRatio)
        dest.writeSerializable(updated)
        dest.writeInt(chaptersCount)
        dest.writeString(lastChapter)
    }
}