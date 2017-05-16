package me.donnie.read.ui.explore.rank

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_discuss.*
import kotlinx.android.synthetic.main.content_book_list.*
import me.donnie.divider.Divider
import me.donnie.read.R
import me.donnie.read.common.base.BaseActivity
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.data.entity.RankList
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class RankActivity : BaseActivity(), RankContract.View {

    @Inject
    lateinit var navigator: RankContract.Navigator

    @Inject
    lateinit var presenter: RankContract.Presenter

    private var adapter: RankAdapter? = null

    var rankComponent: RankComponent? = null

    companion object {
        @JvmStatic fun getCallingIntent(context: Context): Intent {
            val callingIntent = Intent(context, RankActivity::class.java)
            return callingIntent
        }
    }

    override fun setupComponent(component: AppComponent?) {
        rankComponent = DaggerRankComponent.builder()
                .appComponent(component)
                .rankModule(RankModule(this))
                .build()
        rankComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_discuss
    }

    override fun initView(savedInstanceState: Bundle?) {
        presenter.attachView(this)
        toolbar.setTitle(R.string.ranking)
        setSupportActionBar(toolbar)
        supportActionBar.let {
            it!!.setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setNavigationOnClickListener { _ -> onBackPressed() }
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        Divider.with(this)
                .hideLastDivider()
                .size(30)
                .color(resources.getColor(R.color.gray_100))
                .build().addTo(rv)

        refresh_layout.setOnRefreshListener {
            presenter.loadAllRank()
        }
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = RankAdapter()
            rv.adapter = adapter

            refresh_layout.isRefreshing = true
            presenter.loadAllRank()
        }

        adapter!!.setOnItemClickListener { adapter, _, position ->
            val item = adapter!!.getItem(position) as RankList.Rank
            navigator.navigateToHotRank(item)
        }
    }

    override fun loadAllRankSuccess(ranks: List<RankList.Rank>) {
        refresh_layout.isRefreshing = false
        adapter!!.addNewData(ranks)
    }

    override fun onError() {
        refresh_layout.isRefreshing = false
        toast!!.toast("error")
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

}