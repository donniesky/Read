package me.donnie.read.ui.detail.review

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_common_rv.*
import me.donnie.read.R
import me.donnie.read.common.base.BaseFragment
import me.donnie.read.data.entity.BookList
import me.donnie.read.data.entity.ReviewList
import me.donnie.read.ui.detail.BookDetailActivity
import me.donnie.read.ui.detail.BookDetailComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/23.
 * @description
 * @version
 */
class BestReviewFragment : BaseFragment(), BestReviewContract.View {

    @Inject
    lateinit var navigator: BestReviewContract.Navigator

    @Inject
    lateinit var presenter: BestReviewContract.Presenter

    private var adapter: BestReviewAdapter? = null

    private var book: BookList.Book? = null

    var bestReviewComponent: BestReviewComponent? = null

    val bookDetailComponent: BookDetailComponent? get() = (activity as BookDetailActivity).bookDetailComponent

    companion object {

        val TAG = BestReviewFragment::class.simpleName

        val PARAM_BOOK_DETAIL = "param_book_detail"

        @JvmStatic fun newInstance(book: BookList.Book): BestReviewFragment {
            val args = Bundle()
            args.putParcelable(PARAM_BOOK_DETAIL, book)
            val fragment = BestReviewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        bestReviewComponent = bookDetailComponent!!.plus(BestReviewModule())
        bestReviewComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_common_rv
    }

    override fun initView(view: View?) {
        presenter.attachView(this)
        book = arguments.getParcelable(PARAM_BOOK_DETAIL)
        val manager = object : LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rv.isNestedScrollingEnabled = false
        rv.layoutManager = manager
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = BestReviewAdapter()
            rv.adapter = adapter

            presenter.loadBestReviews(book!!._id)
        }
    }

    override fun loadBestReviewsSuccess(reviews: ReviewList) {
        adapter!!.addNewData(reviews.reviews)
    }

    override fun onError() {
        toast!!.toast("error")
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}