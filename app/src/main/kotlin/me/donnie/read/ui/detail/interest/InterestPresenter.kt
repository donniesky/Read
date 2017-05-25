package me.donnie.read.ui.detail.interest

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.common.utils.SchedulerTransformer
import me.donnie.read.data.BookRepository
import me.donnie.read.data.entity.BookList
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/24.
 * @description
 * @version
 */
class InterestPresenter @Inject constructor(val repository: BookRepository) : InterestContract.Presenter {

    companion object {
        private val TAG = InterestPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: InterestContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: InterestContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }

    override fun loadRecommendBooks(bookId: String) {
        disposable.add(repository.getRecommendBooks(bookId)
                .compose(SchedulerTransformer<BookList>())
                .subscribe({ books -> onRecommendBooksResponse(books.books) },
                        { e -> Timber.tag(TAG).e(e.message)
                            view!!.onError() }))
    }

    override fun onRecommendBooksResponse(books: List<BookList.Book>) {
        view!!.loadRecommendBookSuccess(books)
    }

}