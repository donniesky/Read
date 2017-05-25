package me.donnie.read.ui.detail

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.common.utils.SchedulerTransformer
import me.donnie.read.data.BookRepository
import me.donnie.read.data.entity.BookDetail
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/22.
 * @description
 * @version
 */
class BookDetailPresenter @Inject constructor(val repository: BookRepository) : BookDetailContract.Presenter {

    companion object {
        private val TAG = BookDetailPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: BookDetailContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: BookDetailContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }

    override fun loadBookDetail(bookId: String) {
        disposable.add(repository.getBookDetail(bookId)
                .compose(SchedulerTransformer<BookDetail>())
                .subscribe({ detail -> onBookDetailResponse(detail) },
                        { e -> Timber.tag(TAG).e(e.message)
                                view!!.onError() }))
    }

    override fun onBookDetailResponse(detail: BookDetail) {
        view!!.loadBookDetailSuccess(detail)
    }

}