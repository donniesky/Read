package me.donnie.read.common.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
class ToastUtil(var context: Context) {

    private val mainHandler: Handler = Handler(Looper.getMainLooper())

    fun toast(content: String) {
        doToast(content, Toast.LENGTH_SHORT)
    }

    fun toast(@StringRes contentResId: Int) {
        doToast(contentResId, Toast.LENGTH_SHORT)
    }

    fun longToast(content: String) {
        doToast(content, Toast.LENGTH_LONG)
    }

    fun longToast(@StringRes contentResId: Int) {
        doToast(contentResId, Toast.LENGTH_LONG)
    }

    fun doToast(content: String, duration: Int) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, content, duration).show()
        } else {
            mainHandler.post { Toast.makeText(context, content, duration).show() }
        }
    }

    fun doToast(@StringRes contentResId: Int, duration: Int) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, contentResId, duration).show()
        } else {
            mainHandler.post { Toast.makeText(context, contentResId, duration).show() }
        }
    }
}