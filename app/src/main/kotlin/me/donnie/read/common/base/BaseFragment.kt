package me.donnie.read.common.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.pwittchen.prefser.library.Prefser
import icepick.Icepick
import me.donnie.read.common.App
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.utils.NetWorkUtil
import me.donnie.read.common.utils.ToastUtil

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
abstract class BaseFragment : Fragment() {

    protected var toast: ToastUtil? = null

    protected var network: NetWorkUtil? = null

    protected var prefser: Prefser? = null

    protected var isPrepared: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setupComponent()
        super.onCreate(savedInstanceState)
        this.toast = appComponent?.toast()
        this.network = appComponent?.netWork()
        this.prefser = appComponent?.prefser()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Icepick.saveInstanceState(this, outState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Icepick.restoreInstanceState(this, savedInstanceState)

        isPrepared = true
        initView(view)
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private val appComponent: AppComponent? get() = (activity.application as App).appComponent

    protected abstract fun setupComponent()

    protected abstract @LayoutRes fun getLayoutResId(): Int

    protected abstract fun initView(view: View?)

    protected abstract fun initData()

}