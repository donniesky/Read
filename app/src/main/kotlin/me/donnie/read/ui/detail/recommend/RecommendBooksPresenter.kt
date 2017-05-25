package me.donnie.read.ui.detail.recommend

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.common.utils.SchedulerTransformer
import me.donnie.read.data.BookRepository
import me.donnie.read.data.entity.BooksList
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/25.
 * @description
 * @version
 */
class RecommendBooksPresenter @Inject constructor(val repository: BookRepository) : RecommendBooksContract.Presenter {

    companion object {
        private val TAG = RecommendBooksPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: RecommendBooksContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: RecommendBooksContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }

    override fun loadRecommendBooks(bookId: String) {
        disposable.add(repository.getRecommendBookList(bookId)
                .compose(SchedulerTransformer<BooksList>())
                .subscribe({ bookList -> if (bookList.booklists.size > 5) {
                    onBooksResponse(bookList.booklists.subList(0, 5))
                } else {
                    onBooksResponse(bookList.booklists)
                }
                },
                        { e -> Timber.tag(TAG).e(e.message)
                          view!!.onError() }))
    }

    override fun onBooksResponse(books: List<BooksList.Books>) {
        view!!.loadRecommendBooksSuccess(books)
    }

}