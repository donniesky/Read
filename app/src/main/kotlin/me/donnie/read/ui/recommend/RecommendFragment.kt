package me.donnie.read.ui.recommend

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_recommend.*
import me.donnie.divider.Divider
import me.donnie.read.R
import me.donnie.read.common.base.BaseFragment
import me.donnie.read.data.entity.BookList
import me.donnie.read.ui.main.MainActivity
import me.donnie.read.ui.main.MainComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class RecommendFragment : BaseFragment(), RecommendContract.View {

    @Inject
    lateinit var navigator: RecommendContract.Navigator

    @Inject
    lateinit var presenter: RecommendContract.Presenter

    var recommendComponent: RecommendComponent? = null

    var adapter: RecommendAdapter? = null

    val mainComponent: MainComponent? get() = (activity as MainActivity).mainComponent

    companion object {

        val TAG = RecommendFragment::class.simpleName

        @JvmStatic fun newInstance(): RecommendFragment {
            val args = Bundle()
            val fragment = RecommendFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        recommendComponent = mainComponent!!.plus(RecommendModule())
        recommendComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_recommend
    }

    override fun initView(view: View?) {
        presenter.attachView(this)

        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        Divider.with(activity)
                .hideLastDivider()
                .size(30)
                .color(resources.getColor(R.color.gray_100))
                .build().addTo(rv)
        refresh_layout.setOnRefreshListener {
            presenter.loadRecommend("male")
        }
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = RecommendAdapter()
            rv.adapter = adapter

            refresh_layout.isRefreshing = true
            state_view.viewState = MultiStateView.VIEW_STATE_LOADING
            presenter.loadRecommend("male")
        }
    }

    override fun loadRecommendSuccess(books: List<BookList.Book>) {
        refresh_layout.isRefreshing = false
        adapter?.addNewData(books)
        state_view.viewState = MultiStateView.VIEW_STATE_CONTENT
    }

    override fun onError() {
        refresh_layout.isRefreshing = false
        if (adapter?.datas!!.size > 0) {
            toast?.toast("error")
        } else {
            state_view.viewState = MultiStateView.VIEW_STATE_ERROR
        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}