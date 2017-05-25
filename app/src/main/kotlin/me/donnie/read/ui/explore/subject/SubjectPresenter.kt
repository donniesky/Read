package me.donnie.read.ui.explore.subject

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.data.BookRepository
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/19.
 * @description
 * @version
 */
class SubjectPresenter @Inject constructor(val repository: BookRepository) : SubjectContract.Presenter {

    companion object {
        private val TAG = SubjectPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: SubjectContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: SubjectContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }


}