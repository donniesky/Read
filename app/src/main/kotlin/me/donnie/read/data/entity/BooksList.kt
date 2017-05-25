package me.donnie.read.data.entity

import android.os.Parcel
import android.os.Parcelable
import me.donnie.read.common.utils.createParcel

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class BooksList(var total: Int = 0,
                var booklists: List<Books>,
                var ok: Boolean) {

    data class Books(var _id: String,
                     var title: String,
                     var author: String,
                     var desc: String,
                     var gender: String,
                     var collectorCount: Int,
                     var cover: String,
                     var bookCount: Int) : Parcelable {
        companion object {
            @JvmField val CREATOR = createParcel { Books(it) }
        }

        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString(),
                source.readInt(),
                source.readString(),
                source.readInt()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(_id)
            dest.writeString(title)
            dest.writeString(author)
            dest.writeString(desc)
            dest.writeString(gender)
            dest.writeInt(collectorCount)
            dest.writeString(cover)
            dest.writeInt(bookCount)
        }
    }

}