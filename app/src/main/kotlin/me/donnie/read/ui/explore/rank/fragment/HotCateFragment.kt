package me.donnie.read.ui.explore.rank.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.content_book_list.*
import me.donnie.divider.Divider
import me.donnie.read.R
import me.donnie.read.common.base.BaseFragment
import me.donnie.read.data.entity.BookList
import me.donnie.read.ui.explore.rank.hot.HotRankActivity
import me.donnie.read.ui.explore.rank.hot.HotRankAdapter
import me.donnie.read.ui.explore.rank.hot.HotRankComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/16.
 * @description
 * @version
 */
class HotCateFragment : BaseFragment(), HotCateContract.View {

    companion object {

        val TAG = HotCateFragment::class.simpleName

        val PARAM_RANK_ID = "rank_id"

        @JvmStatic fun newInstance(id: String): HotCateFragment {
            val args = Bundle()
            args.putString(PARAM_RANK_ID, id)
            val fragment = HotCateFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var id: String? = null

    @Inject
    lateinit var navigator: HotCateContract.Navigator

    @Inject
    lateinit var presenter: HotCateContract.Presenter

    private var adapter: HotRankAdapter? = null

    var hotCateComponent: HotCateComponent? = null

    val hotRankComponent: HotRankComponent? get() = (activity as HotRankActivity).hotRankComponent

    override fun setupComponent() {
        hotCateComponent = hotRankComponent!!.plus(HotCateModule())
        hotCateComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.content_book_list
    }

    override fun initView(view: View?) {
        presenter.attachView(this)

        id = arguments.getString(PARAM_RANK_ID, "")

        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        Divider.with(activity)
                .hideLastDivider()
                .size(30)
                .color(resources.getColor(R.color.gray_100))
                .build().addTo(rv)

        refresh_layout.setOnRefreshListener {
            presenter.loadHotRank(id!!)
        }
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = HotRankAdapter()
            rv.adapter = adapter

            refresh_layout.isRefreshing = true
            presenter.loadHotRank(id!!)
        }

        adapter!!.setOnItemClickListener { adapter, _, position ->
            val item: BookList.Book = adapter.getItem(position) as BookList.Book
            navigator.navigateToDetail(item)
        }
    }

    override fun loadRankSuccess(books: List<BookList.Book>) {
        refresh_layout.isRefreshing = false
        adapter!!.addNewData(books)
    }

    override fun onError() {
        refresh_layout.isRefreshing = false
        toast!!.toast("error")
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}