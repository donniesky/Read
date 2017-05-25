package me.donnie.read.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.content_book_detail.*
import me.donnie.read.BuildConfig
import me.donnie.read.R
import me.donnie.read.common.base.BaseActivity
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.utils.friendly_time
import me.donnie.read.data.entity.BookDetail
import me.donnie.read.data.entity.BookList
import me.donnie.read.ui.detail.interest.InterestFragment
import me.donnie.read.ui.detail.recommend.RecommendBooksFragment
import me.donnie.read.ui.detail.review.BestReviewFragment
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/22.
 * @description
 * @version
 */
class BookDetailActivity : BaseActivity(), BookDetailContract.View {

    private var bestReviewFragment: Fragment? = null

    private var interestFragment: Fragment? = null

    private var recommendBooksFragment: Fragment? = null

    @Inject
    lateinit var navigator: BookDetailContract.Navigator

    @Inject
    lateinit var presenter: BookDetailContract.Presenter

    private var book: BookList.Book? = null

    var bookDetailComponent: BookDetailComponent? = null

    companion object {
        val TAG = BookDetailActivity::class.simpleName
        val PARAM_BOOK = "param_book"

        @JvmStatic fun getCallingIntent(context: Context): Intent {
            val callingIntent = Intent(context, BookDetailActivity::class.java)
            return callingIntent
        }
    }

    override fun setupComponent(component: AppComponent?) {
        bookDetailComponent = DaggerBookDetailComponent.builder()
                .appComponent(component!!)
                .bookDetailModule(BookDetailModule(this))
                .build()
        bookDetailComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_book_detail
    }

    override fun initView(savedInstanceState: Bundle?) {
        presenter.attachView(this)

        book = intent.getParcelableExtra(PARAM_BOOK)
        toolbar.title = book!!.title
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        if (savedInstanceState == null) {
            presenter.loadBookDetail(book!!._id)
        }
        initFragments(savedInstanceState)
    }

    private fun initFragments(savedInstanceState: Bundle?) {
        val manager = supportFragmentManager
        bestReviewFragment = manager.findFragmentByTag(BestReviewFragment.TAG)
        interestFragment = manager.findFragmentByTag(InterestFragment.TAG)
        recommendBooksFragment = manager.findFragmentByTag(RecommendBooksFragment.TAG)

        if (bestReviewFragment == null) {
            bestReviewFragment = BestReviewFragment.newInstance(book!!)
        }

        if (interestFragment == null) {
            interestFragment = InterestFragment.newInstance(book!!)
        }

        if (recommendBooksFragment == null) {
            recommendBooksFragment = RecommendBooksFragment.newInstance(book!!)
        }

        if (savedInstanceState == null) {
            switchReviewFragment()
            switchInterestFragment()
            switchRecommendFragment()
        }
    }

    private fun switchReviewFragment(): Boolean {
        if (bestReviewFragment!!.isAdded) {
            return false
        }

        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        val currentFragment = manager.findFragmentById(R.id.best_comments)
        if (currentFragment != null) {
            ft.detach(currentFragment)
        }
        if (bestReviewFragment!!.isDetached) {
            ft.attach(bestReviewFragment)
        } else {
            ft.add(R.id.best_comments, bestReviewFragment, BestReviewFragment.TAG)
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
        manager.executePendingTransactions()
        return true
    }

    private fun switchInterestFragment(): Boolean {
        if (interestFragment!!.isAdded) {
            return false
        }

        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        val currentFragment = manager.findFragmentById(R.id.recommend_subjects)
        if (currentFragment != null) {
            ft.detach(currentFragment)
        }
        if (interestFragment!!.isDetached) {
            ft.attach(interestFragment)
        } else {
            ft.add(R.id.recommend_subjects, interestFragment, InterestFragment.TAG)
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
        manager.executePendingTransactions()
        return true
    }

    private fun switchRecommendFragment(): Boolean {
        if (recommendBooksFragment!!.isAdded) {
            return false
        }

        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        val currentFragment = manager.findFragmentById(R.id.recommend)
        if (currentFragment != null) {
            ft.detach(currentFragment)
        }
        if (recommendBooksFragment!!.isDetached) {
            ft.attach(recommendBooksFragment)
        } else {
            ft.add(R.id.recommend, recommendBooksFragment, InterestFragment.TAG)
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
        manager.executePendingTransactions()
        return true
    }

    override fun initData() {

    }

    override fun loadBookDetailSuccess(detail: BookDetail) {
        Glide.with(this).load(BuildConfig.IMG_BASE_URL+detail.cover)
                .centerCrop()
                .crossFade()
                .into(img)

        novel_title.text = detail.title
        name.text = detail.author
        type.text = detail.updated.friendly_time()+" | "+detail.cat

        desc.text = detail.longIntro
        community.text = detail.title+"的社区"
        count.text = "共有"+detail.postCount.toString()+"个帖子"
    }

    override fun onError() {

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

}