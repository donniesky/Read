package me.donnie.read.ui.community.discuss

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.common.utils.SchedulerTransformer
import me.donnie.read.data.BookRepository
import me.donnie.read.data.entity.PostList
import timber.log.Timber
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class DiscussPresenter @Inject constructor(val repository: BookRepository) : DiscussContract.Presenter {

    companion object {
        private val TAG = DiscussPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var start: Int = 0

    private var view: DiscussContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: DiscussContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }

    fun refresh() {
        this.start = 0
        loadDiscussList(this.start)
    }

    fun loadmore() {
        this.start += 20
        loadDiscussList(this.start)
    }

    override fun loadDiscussList(page: Int) {
        disposable.add(repository.getComplexDiscussList(page)
                .compose(SchedulerTransformer<PostList>())
                .subscribe({ discuss -> onDiscussListResponse(discuss.posts)}, {
                    e -> run {
                        Timber.tag(TAG).e(e.message)
                        view!!.onError()
                    }
                }))
    }

    override fun onDiscussListResponse(discuss: List<PostList.Post>) {
        view!!.loadDiscussListSuccess(discuss)
    }

}