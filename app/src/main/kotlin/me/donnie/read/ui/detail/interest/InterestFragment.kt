package me.donnie.read.ui.detail.interest

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_common_rv.*
import me.donnie.divider.Divider
import me.donnie.read.R
import me.donnie.read.common.base.BaseFragment
import me.donnie.read.data.entity.BookList
import me.donnie.read.ui.detail.BookDetailActivity
import me.donnie.read.ui.detail.BookDetailComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/24.
 * @description
 * @version
 */
class InterestFragment : BaseFragment(), InterestContract.View {

    @Inject
    lateinit var navigator: InterestContract.Navigator

    @Inject
    lateinit var presenter: InterestContract.Presenter

    private var adapter: InterestAdapter? = null

    private var book: BookList.Book? = null

    var interestComponent: InterestComponent? = null

    val bookDetailComponent: BookDetailComponent? get() = (activity as BookDetailActivity).bookDetailComponent

    companion object {

        val TAG = InterestFragment::class.simpleName

        val PARAM_BOOK_DETAIL = "param_book_detail"

        @JvmStatic fun newInstance(book: BookList.Book): InterestFragment {
            val args = Bundle()
            args.putParcelable(PARAM_BOOK_DETAIL, book)
            val fragment = InterestFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        interestComponent = bookDetailComponent!!.plus(InterestModule())
        interestComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_common_rv
    }

    override fun initView(view: View?) {
        presenter.attachView(this)
        book = arguments.getParcelable(PARAM_BOOK_DETAIL)

        val manager = object : LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        rv.isNestedScrollingEnabled = false
        rv.layoutManager = manager
        Divider.with(activity)
                .size(40)
                .color(resources.getColor(R.color.gray_100))
                .build().addTo(rv)
    }

    override fun initData() {
        if (adapter != null) {
            rv.adapter = adapter
        } else {
            adapter = InterestAdapter()
            rv.adapter = adapter

            presenter.loadRecommendBooks(book!!._id)
        }
    }

    override fun loadRecommendBookSuccess(books: List<BookList.Book>) {
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