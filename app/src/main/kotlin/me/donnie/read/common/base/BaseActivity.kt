package me.donnie.read.common.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
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
abstract class BaseActivity : AppCompatActivity() {

    protected var toast: ToastUtil? = null

    protected var network: NetWorkUtil? = null

    private val appComponent: AppComponent? get() = (application as App).appComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        setupComponent(appComponent)
        super.onCreate(savedInstanceState)
        Icepick.restoreInstanceState(this, savedInstanceState)
        //binding = DataBindingUtil.setContentView<T>(this, getLayoutResId())
        setContentView(getLayoutResId())
        this.toast = appComponent?.toast()
        this.network = appComponent?.netWork()
        initView(savedInstanceState)
        initData()
    }

    protected abstract fun setupComponent(component: AppComponent?)
    protected abstract @LayoutRes fun getLayoutResId(): Int
    protected abstract fun initView(savedInstanceState: Bundle?)
    protected abstract fun initData()

}