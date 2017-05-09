package me.donnie.read.common.base

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
abstract class LazyFragment : BaseFragment() {

    protected var isShow: Boolean = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            this.isShow = true
            onVisible()
        } else {
            this.isShow = false
            onInVisible()
        }
    }

    protected abstract fun lazyload()

    protected fun onVisible() {
        if (isShow && isPrepared) {
            lazyload()
        }
    }

    protected fun onInVisible(){}
}