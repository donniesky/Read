package me.donnie.read.ui.recommend

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.common.utils.SchedulerTransformer
import me.donnie.read.data.BookRepository
import me.donnie.read.data.entity.Book
import me.donnie.read.data.entity.Result
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class RecommendPresenter @Inject constructor(val bookRepository: BookRepository) : RecommendContract.Presenter {

    companion object {
        private val TAG = RecommendPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: RecommendContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: RecommendContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }

    override fun loadRecommend(gender: String) {
        disposable.add(bookRepository.getRecommend(gender)
                .compose(SchedulerTransformer<Result<Book>>())
                .subscribe({ book ->
                    onRecommendResponse(book.books) },
                    { e ->
                        Timber.tag(TAG).e(e.message)
                        view?.onError()
                    }))
    }

    override fun onRecommendResponse(books: List<Book>) {
        view?.loadRecommendSuccess(books)
    }

}