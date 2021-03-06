package me.donnie.read.common.utils

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Parcel
import android.os.Parcelable
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import timber.log.Timber
import java.lang.reflect.Field
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */

fun Context.getStatusBarHeight(): Int {
    var c: Class<*>? = null
    var obj: Any? = null
    var field: Field? = null
    var x: Int = 0
    var statusBarHeight: Int = 0
    try {
        c = Class.forName("com.android.internal.R\$dimen")
        obj = c!!.newInstance()
        field = c.getField("status_bar_height")
        x = field!!.get(obj).toString().toInt()
        statusBarHeight = this.resources.getDimensionPixelSize(x)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return statusBarHeight
}

fun Activity.setXiaomiDarkMode(): Boolean {
    var clazz = this.window.javaClass
    try {
        var darkModeFlag = 0
        val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
        val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
        darkModeFlag = field.getInt(layoutParams)
        val extraFlagField = clazz.getMethod("setExtraFlags", Int.javaClass, Int.javaClass)
        extraFlagField.invoke(this.window, darkModeFlag, darkModeFlag)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

fun Activity.setMeizuDarkMode(): Boolean {
    var result = false
    try {
        val lp = this.window.attributes
        val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
        val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
        darkFlag.isAccessible = true
        meizuFlags.isAccessible = true
        val bit = darkFlag.getInt(null)
        var value = meizuFlags.getInt(lp)
        value = value or bit
        meizuFlags.setInt(lp, value)
        this.window.attributes = lp
        result = true
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result
}

fun Bitmap.circleCrop(pool: BitmapPool): Bitmap {
    val size = Math.min(this.width, this.height)
    val x = (this.width - size)/2
    val y = (this.height - size)/2

    var squared = Bitmap.createBitmap(this, x, y, size, size)

    var result = pool.get(size, size, Bitmap.Config.ARGB_8888)
    if (result == null) {
        result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    }

    var canvas = Canvas(result)
    var paint = Paint()
    paint.shader = BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    paint.isAntiAlias = true
    var r = size/2f
    canvas.drawCircle(r, r, r, paint)
    return result
}

fun Bitmap.roundCrop(pool: BitmapPool, radius: Float): Bitmap {
    var result = pool.get(this.width, this.height, Bitmap.Config.ARGB_8888)
    if (result == null) {
        result = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    }

    var canvas = Canvas(result)
    var paint = Paint()
    paint.shader = BitmapShader(this, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    paint.isAntiAlias = true
    val rectF = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
    canvas.drawRoundRect(rectF, radius, radius, paint)
    return result
}

inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)

            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)

        }


fun BottomNavigationView.disableShiftingMode() {
    val menuView: BottomNavigationMenuView = this.getChildAt(0) as BottomNavigationMenuView

    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0..menuView.childCount - 1) {
            val item: BottomNavigationItemView = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        Timber.tag("BottomNavigationView").e(e.message)
    } catch (e: IllegalAccessException) {
        Timber.tag("BottomNavigationView").e(e.message)
    }
}

fun CharSequence.isEmpty(str: CharSequence?) {
    if (str == null || str.isEmpty()) {

    }
}

fun View.hideView() {
    visibility = View.GONE
}

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.Invisible() {
    visibility = View.INVISIBLE
}

object dateFormater : ThreadLocal<SimpleDateFormat>() {
    override fun initialValue(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    }
}

object dateFormater1 : ThreadLocal<SimpleDateFormat>() {
    override fun initialValue(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    }
}

fun Date.friendly_time(): String {
    if (this == null) {
        return ""
    }
    var ftime = ""
    val cal = Calendar.getInstance()

    val curDate = dateFormater.get().format(cal.time)
    val paramDate = dateFormater.get().format(this)

    if (curDate.equals(paramDate)) {
        val minute = ((cal.timeInMillis - this.time) / 60000) as Int
        val hour = ((cal.timeInMillis - this.time) / 3600000) as Int
        if (minute == 0) {
            ftime = "刚刚"
        } else {
            if (hour == 0) {
                ftime = Math.max((cal.timeInMillis - this.time) / 60000, 1).toString() + "分钟前"
            } else {
                ftime = hour.toString() + "小时前"
            }
        }
        return ftime
    }

    val lt = this.time / 86400000
    val ct = cal.timeInMillis / 86400000
    val days = (ct - lt).toInt()

    if (days == 0) {
        val hour = ((cal.timeInMillis - this.time) / 3600000).toInt()
        if (hour == 0) {
            ftime = Math.max((cal.timeInMillis - this.time) / 60000, 1).toString() + "分钟前"
        } else {
            ftime = hour.toString() + "小时前"
        }
    } else if (days == 1) {
        ftime = "昨天"
    } else if (days == 2) {
        ftime = "前天"
    } else if (days in 3..30) {
        ftime = days.toString() + "天前"
    } else if (days >= 31 && days <= 2 * 31) {
        ftime = "一个月前"
    } else if (days > 2 * 31 && days <= 3 * 31) {
        ftime = "两个月前"
    } else if (days > 3 * 31 && days <= 4 * 31) {
        ftime = "三个月前"
    } else {
        ftime = dateFormater1.get().format(this)
    }
    return ftime
}