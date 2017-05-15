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
class PostList(var posts: List<Post>,
               var ok: Boolean) {

    data class Post(var _id: String,
                    var title: String,
                    var content: String,
                    var author: Author,
                    var type: String,
                    var isStopPriority: Boolean,
                    var deleted: Boolean,
                    var likeCount: Int,
                    var block: String,
                    var haveImage: Boolean,
                    var state: String,
                    var updated: Date,
                    var created: Date,
                    var commentCount: Int,
                    var voteCount: Int,
                    var shareLink: String) : Parcelable {
        companion object {
            @JvmField val CREATOR = createParcel { Post(it) }
        }

        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString(),
                source.readParcelable<Author>(Author::class.java.classLoader),
                source.readString(),
                1 == source.readInt(),
                1 == source.readInt(),
                source.readInt(),
                source.readString(),
                1 == source.readInt(),
                source.readString(),
                source.readSerializable() as Date,
                source.readSerializable() as Date,
                source.readInt(),
                source.readInt(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(_id)
            dest.writeString(title)
            dest.writeString(content)
            dest.writeParcelable(author, 0)
            dest.writeString(type)
            dest.writeInt((if (isStopPriority) 1 else 0))
            dest.writeInt((if (deleted) 1 else 0))
            dest.writeInt(likeCount)
            dest.writeString(block)
            dest.writeInt((if (haveImage) 1 else 0))
            dest.writeString(state)
            dest.writeSerializable(updated)
            dest.writeSerializable(created)
            dest.writeInt(commentCount)
            dest.writeInt(voteCount)
            dest.writeString(shareLink)
        }
    }

}