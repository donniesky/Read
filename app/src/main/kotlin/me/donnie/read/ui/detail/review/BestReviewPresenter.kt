package me.donnie.read.ui.detail.review

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.common.utils.SchedulerTransformer
import me.donnie.read.data.BookRepository
import me.donnie.read.data.entity.ReviewList
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/23.
 * @description
 * @version
 */
class BestReviewPresenter @Inject constructor(val repository: BookRepository) : BestReviewContract.Presenter {

    companion object {
        private val TAG = BestReviewPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: BestReviewContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: BestReviewContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }

    override fun loadBestReviews(id: String) {
        disposable.add(repository.getBestReviews(id)
                .compose(SchedulerTransformer<ReviewList>())
                .subscribe({ reviews -> onReviewsResponse(reviews) }
                        ,{ e ->  Timber.tag(TAG).e(e.message)
                                  view!!.onError()} ))
    }

    override fun onReviewsResponse(reviews: ReviewList) {
        view!!.loadBestReviewsSuccess(reviews)
    }

}