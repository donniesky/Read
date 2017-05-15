package me.donnie.read.common.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class GlideCircleTransform(val context: Context) : BitmapTransformation(context) {

    override fun getId(): String {
        return javaClass.name
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return toTransform.circleCrop(pool)
    }


}