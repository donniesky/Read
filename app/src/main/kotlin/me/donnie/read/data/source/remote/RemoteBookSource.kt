package me.donnie.read.data.source.remote

import io.reactivex.Observable
import me.donnie.read.data.entity.Book
import me.donnie.read.data.entity.Result
import me.donnie.read.data.source.api.BookApi
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class RemoteBookSource @Inject constructor(val bookApi: BookApi) {

    fun getRecommend(gender: String): Observable<Result<Book>> {
        return bookApi.getRecommend(gender)
    }

}