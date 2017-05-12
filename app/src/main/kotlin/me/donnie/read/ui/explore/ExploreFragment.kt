package me.donnie.read.ui.explore

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_recommend.*
import me.donnie.read.R
import me.donnie.read.common.base.BaseFragment
import me.donnie.read.data.entity.Community
import me.donnie.read.ui.community.CommunityAdapter
import me.donnie.read.ui.main.MainActivity
import me.donnie.read.ui.main.MainComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
class ExploreFragment : BaseFragment() {

    @Inject
    lateinit var navigator: ExploreContract.Navigator

    var exploreComponent: ExploreComponent? = null

    private var adapter: CommunityAdapter? = null

    val mainComponent: MainComponent? get() = (activity as MainActivity).mainComponent

    companion object {

        val TAG = ExploreFragment::class.simpleName

        @JvmStatic fun newInstance(): ExploreFragment {
            val args = Bundle()
            val fragment = ExploreFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        exploreComponent = mainComponent!!.plus(ExploreModule())
        exploreComponent!!.inject(this)
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

        adapter!!.setOnItemClickListener { _, _, position -> run {
            when(position) {
                0 -> navigator.navigateToRank()
            }
        } }
    }

    private fun getData(): List<Community> {
        var communities = arrayListOf<Community>()
        val strings = resources.getStringArray(R.array.explore_strings)
        val icons = resources.obtainTypedArray(R.array.explore_icons)
        (0..strings.size-1).mapTo(communities) {
            Community(strings[it],icons.getDrawable(it))
        }
        icons.recycle()
        return  communities
    }

}