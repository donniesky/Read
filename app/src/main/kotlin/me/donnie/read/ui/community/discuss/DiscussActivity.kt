package me.donnie.read.ui.community.discuss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_discuss.*
import me.donnie.divider.Divider
import me.donnie.read.R
import me.donnie.read.common.base.BaseActivity
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.data.entity.PostList
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class DiscussActivity : BaseActivity(), DiscussContract.View {

    @Inject
    lateinit var navigator: DiscussContract.Navigator

    @Inject
    lateinit var presenter: DiscussContract.Presenter

    var discussComponent: DiscussComponent? = null

    private var adapter: DiscussAdapter? = null

    companion object {
        @JvmStatic fun getCallingIntent(context: Context): Intent {
            val callingIntent = Intent(context, DiscussActivity::class.java)
            return callingIntent
        }
    }

    override fun setupComponent(component: AppComponent?) {
        discussComponent = DaggerDiscussComponent.builder()
                .appComponent(component!!)
                .discussModule(DiscussModule(this))
                .build()
        discussComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_discuss
    }

    override fun initView(savedInstanceState: Bundle?) {
        presenter.attachView(this)
        toolbar.setTitle(R.string.complex_discuss_area)
        setSupportActionBar(toolbar)
        supportActionBar.let {
            it!!.setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setNavigationOnClickListener { view -> onBackPressed() }
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        Divider.with(this)
                .hideLastDivider()
                .size(30)
                .color(resources.getColor(R.color.gray_100))
                .build().addTo(rv)

        refresh_layout.setOnRefreshListener {
            (presenter as DiscussPresenter).refresh()
        }
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = DiscussAdapter()
            rv.adapter = adapter

            refresh_layout.isRefreshing = true
            (presenter as DiscussPresenter).refresh()
        }
    }

    override fun loadDiscussListSuccess(discuss: List<PostList.Post>) {
        refresh_layout.isRefreshing = false
        adapter?.addNewData(discuss)
    }

    override fun onError() {
        refresh_layout.isRefreshing = false
        toast?.toast("error")
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

}