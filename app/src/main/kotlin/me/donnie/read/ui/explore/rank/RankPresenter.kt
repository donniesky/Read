package me.donnie.read.ui.explore.rank

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.common.utils.SchedulerTransformer
import me.donnie.read.data.BookRepository
import me.donnie.read.data.entity.RankList
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class RankPresenter @Inject constructor(val repository: BookRepository) : RankContract.Presenter {

    companion object {
        private val TAG = RankPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: RankContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: RankContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }

    override fun loadAllRank() {
        disposable.add(repository.getRank()
                .compose(SchedulerTransformer<RankList>())
                .subscribe({ rankList -> onAllRankResponse(rankList.male) },
                        { e -> run {
                            Timber.tag(TAG).e(e.message)
                            view!!.onError()
                        } }))
    }

    override fun onAllRankResponse(ranks: List<RankList.Rank>) {
        view!!.loadAllRankSuccess(ranks)
    }

}