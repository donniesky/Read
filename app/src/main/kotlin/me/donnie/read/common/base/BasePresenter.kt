package me.donnie.read.common.base

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
interface BasePresenter<V : BaseView> {

    fun attachView(view: V?)

    fun detachView()

}