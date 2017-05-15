package me.donnie.read.common.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class GlideRoundTransform : BitmapTransformation {

    private var radius: Float = 0f

    constructor(context: Context): this(context, 4)

    constructor(context: Context, dp: Int): super(context) {
        this.radius = Resources.getSystem().displayMetrics.density * dp
    }

    override fun getId(): String {
        return javaClass.name + Math.round(radius)
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return toTransform.roundCrop(pool, radius)
    }


}