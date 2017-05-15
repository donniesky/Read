package me.donnie.read.ui.explore.rank.hot

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.common.utils.SchedulerTransformer
import me.donnie.read.data.BookRepository
import me.donnie.read.data.entity.BookList
import me.donnie.read.data.entity.RankDetail
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/15.
 * @description
 * @version
 */
class HotRankPresenter @Inject constructor(val repository: BookRepository) : HotRankContract.Presenter {

    companion object {
        private val TAG = HotRankPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: HotRankContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: HotRankContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }

    override fun loadHotRank(id: String) {
        disposable.add(repository.getRank(id)
                .compose(SchedulerTransformer<RankDetail>())
                .subscribe({ rankDetail -> onHotRankResponse(rankDetail.ranking.books!!)},
                        {e -> Timber.tag(TAG).e(e.message)
                            view!!.onError()}))
    }

    override fun onHotRankResponse(books: List<BookList.Book>) {
        view!!.loadRankSuccess(books)
    }

}