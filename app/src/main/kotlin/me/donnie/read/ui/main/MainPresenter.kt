package me.donnie.read.ui.main

import com.github.pwittchen.prefser.library.Prefser
import io.reactivex.disposables.CompositeDisposable
import me.donnie.read.data.BookRepository
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class MainPresenter @Inject constructor(val bookRepository: BookRepository) : MainContract.Presenter {

    companion object {
        private val TAG = MainPresenter::class.simpleName
    }

    @Inject
    lateinit var prefser: Prefser

    private var view: MainContract.View? = null

    private var disposable = CompositeDisposable()

    override fun attachView(view: MainContract.View?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable.dispose()
    }


}