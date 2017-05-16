package me.donnie.read.ui.explore.rank.hot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_discuss.*
import kotlinx.android.synthetic.main.activity_hot_rank.*
import kotlinx.android.synthetic.main.content_book_list.*
import me.donnie.divider.Divider
import me.donnie.read.R
import me.donnie.read.common.base.BaseActivity
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.data.entity.BookList
import me.donnie.read.data.entity.RankList
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class HotRankActivity : BaseActivity(), HotRankContract.View {

    @Inject
    lateinit var navigator: HotRankContract.Navigator

    @Inject
    lateinit var presenter: HotRankContract.Presenter

    var hotRankComponent: HotRankComponent? = null

    var rank: RankList.Rank? = null

    var adapter: HotRankAdapter? = null

    var tabAdapter: HotRankTabAdapter? = null

    companion object {
        @JvmStatic fun getCallingIntent(context: Context): Intent {
            val callingIntent = Intent(context, HotRankActivity::class.java)
            return callingIntent
        }
    }

    override fun setupComponent(component: AppComponent?) {
        hotRankComponent = DaggerHotRankComponent.builder()
                .appComponent(component!!)
                .hotRankModule(HotRankModule(this))
                .build()
        hotRankComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        rank = intent.getParcelableExtra("rank")
        if (rank?.monthRank != null) {
            return R.layout.activity_hot_rank
        } else {
            return R.layout.activity_discuss
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        presenter.attachView(this)

        if (rank?.monthRank != null) {
            hot_toolbar.title = rank!!.shortTitle
            setSupportActionBar(hot_toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            hot_toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
            val datas: MutableList<String> = mutableListOf()
            datas.add(rank!!._id)
            datas.add(rank!!.monthRank!!)
            datas.add(rank!!.totalRank!!)
            tabAdapter = HotRankTabAdapter(this, supportFragmentManager, datas)
            viewpager!!.adapter = tabAdapter
            viewpager!!.offscreenPageLimit = datas.size
            tab.setupWithViewPager(viewpager)
        } else {
            toolbar.title = rank!!.shortTitle
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
            rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            Divider.with(this)
                    .hideLastDivider()
                    .size(30)
                    .color(resources.getColor(R.color.gray_100))
                    .build().addTo(rv)

            refresh_layout.setOnRefreshListener {
                presenter.loadHotRank(rank!!._id)
            }
        }

    }

    override fun initData() {
        if (rank?.monthRank != null) {

        } else {
            if (adapter != null) {
                rv.adapter = adapter
            } else {
                adapter = HotRankAdapter()
                rv.adapter = adapter

                refresh_layout.isRefreshing = true
                presenter.loadHotRank(rank!!._id)
            }
        }
    }

    override fun loadRankSuccess(books: List<BookList.Book>) {
        if (rank?.monthRank != null) {

        } else {
            refresh_layout.isRefreshing = false
            adapter!!.addNewData(books)
        }
    }

    override fun onError() {
        if (rank?.monthRank == null) {
            refresh_layout.isRefreshing = false
            toast!!.toast("error")
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

}