package me.donnie.read.data

import io.reactivex.Observable
import me.donnie.read.data.entity.Book
import me.donnie.read.data.entity.Result
import me.donnie.read.data.source.remote.RemoteBookSource
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class BookRepository @Inject constructor(val remoteBookSource: RemoteBookSource) {

    fun getRecommend(gender: String): Observable<Result<Book>> {
        return remoteBookSource.getRecommend(gender)
    }

}