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
data class Author(var _id: String,
             var avatar: String,
             var nickname: String,
             var activityAvatar: String,
             var type: String,
             var lv: Int,
             var gender: String) : Parcelable {

    companion object {
        @JvmField val CREATOR = createParcel { Author(it) }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(_id)
        dest.writeString(avatar)
        dest.writeString(nickname)
        dest.writeString(activityAvatar)
        dest.writeString(type)
        dest.writeInt(lv)
        dest.writeString(gender)
    }
}