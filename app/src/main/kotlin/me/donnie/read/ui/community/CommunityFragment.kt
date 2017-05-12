package me.donnie.read.ui.community

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_recommend.*
import me.donnie.read.R
import me.donnie.read.common.base.BaseFragment
import me.donnie.read.data.entity.Community
import me.donnie.read.ui.main.MainActivity
import me.donnie.read.ui.main.MainComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
class CommunityFragment : BaseFragment() {

    @Inject
    lateinit var navigator: CommunityContract.Navigator

    var communityComponent: CommunityComponent? = null

    private var adapter: CommunityAdapter? = null

    val mainComponent: MainComponent? get() = (activity as MainActivity).mainComponent

    companion object {

        val TAG = CommunityFragment::class.simpleName

        @JvmStatic fun newInstance(): CommunityFragment {
            val args = Bundle()
            val fragment = CommunityFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        communityComponent = mainComponent!!.plus(CommunityModule())
        communityComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_recommend
    }

    override fun initView(view: View?) {
        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        refresh_layout.isEnabled = false
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = CommunityAdapter()
            rv.adapter = adapter
            adapter!!.addNewData(getData())
        }

        adapter!!.setOnItemClickListener { _, _, position ->
            run {
                when (position) {
                    0 -> navigator.navigateToDiscussList()
                }
            }
        }
    }

    private fun getData(): List<Community> {
        var communities = arrayListOf<Community>()
        val strings = resources.getStringArray(R.array.community_strings)
        val icons = resources.obtainTypedArray(R.array.community_icons)
        (0..strings.size-1).mapTo(communities) {
            Community(strings[it],icons.getDrawable(it))
        }
        icons.recycle()
        return  communities
    }
}