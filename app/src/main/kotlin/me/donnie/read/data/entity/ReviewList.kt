package me.donnie.read.data.entity

import android.os.Parcel
import android.os.Parcelable
import me.donnie.read.common.utils.createParcel

/**
 * @author donnieSky
 * @created_at 2017/5/22.
 * @description
 * @version
 */
class ReviewList(var reviews: List<Review>,
                 val ok: Boolean) {

    data class Review(var _id: String,
                      var rating: Int,
                      var content: String,
                      var title: String,
                      var author: Author,
                      var likeCount: Int,
                      var state: String,
                      var updated: String,
                      var created: String,
                      var commentCount: Int) : Parcelable {
        companion object {
            @JvmField val CREATOR = createParcel { Review(it) }
        }

        constructor(source: Parcel) : this(
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readString(),
                source.readParcelable<Author>(Author::class.java.classLoader),
                source.readInt(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readInt()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(_id)
            dest.writeInt(rating)
            dest.writeString(content)
            dest.writeString(title)
            dest.writeParcelable(author, 0)
            dest.writeInt(likeCount)
            dest.writeString(state)
            dest.writeString(updated)
            dest.writeString(created)
            dest.writeInt(commentCount)
        }
    }
}