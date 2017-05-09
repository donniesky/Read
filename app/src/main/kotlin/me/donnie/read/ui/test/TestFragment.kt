package me.donnie.read.ui.test

import android.os.Bundle
import android.view.View
import me.donnie.read.R
import me.donnie.read.common.base.BaseFragment

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class TestFragment : BaseFragment() {

    companion object {

        val TAG = TestFragment::class.simpleName

        @JvmStatic fun newInstance(): TestFragment {
            val args = Bundle()
            val fragment = TestFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {

    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_test
    }

    override fun initView(view: View?) {
    }

    override fun initData() {
    }


}