package me.donnie.read.ui.detail.recommend

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_common_rv.*
import me.donnie.divider.Divider
import me.donnie.read.R
import me.donnie.read.common.base.BaseFragment
import me.donnie.read.data.entity.BookList
import me.donnie.read.data.entity.BooksList
import me.donnie.read.ui.detail.BookDetailActivity
import me.donnie.read.ui.detail.BookDetailComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/25.
 * @description
 * @version
 */
class RecommendBooksFragment : BaseFragment(), RecommendBooksContract.View {

    @Inject
    lateinit var navigator: RecommendBooksContract.Navigator

    @Inject
    lateinit var presenter: RecommendBooksContract.Presenter

    private var adapter: RecommendBooksAdapter? = null

    private var book: BookList.Book? = null

    var recommendBooksComponent: RecommendBooksComponent? = null

    val bookDetailComponent: BookDetailComponent? get() = (activity as BookDetailActivity).bookDetailComponent

    companion object {

        val TAG = RecommendBooksFragment::class.simpleName

        val PARAM_BOOK_DETAIL = "param_book_detail"

        @JvmStatic fun newInstance(book: BookList.Book): RecommendBooksFragment {
            val args = Bundle()
            args.putParcelable(PARAM_BOOK_DETAIL, book)
            val fragment = RecommendBooksFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        recommendBooksComponent = bookDetailComponent!!.plus(RecommendBooksModule())
        recommendBooksComponent!!.inject(this)
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
        rv.layoutManager = manager
        rv.isNestedScrollingEnabled = false
        Divider.with(activity)
                .size(40)
                .color(resources.getColor(R.color.gray_100))
                .build().addTo(rv)
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = RecommendBooksAdapter()
            rv.adapter = adapter

            presenter.loadRecommendBooks(book!!._id)
        }
    }

    override fun loadRecommendBooksSuccess(books: List<BooksList.Books>) {
        adapter!!.addNewData(books)
    }

    override fun onError() {
        toast!!.toast("error")
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

}