package me.donnie.read.common.utils

import android.os.Parcel
import android.os.Parcelable
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.view.View
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
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
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }
}

object dateFormater1 : ThreadLocal<SimpleDateFormat>() {
    override fun initialValue(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd")
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