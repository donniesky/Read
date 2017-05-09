package me.donnie.read.data.source.api

import io.reactivex.Observable
import me.donnie.read.data.entity.Book
import me.donnie.read.data.entity.Result
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
interface BookApi {

    @GET("/book/recommend")
    fun getRecommend(@Query("gender") gender: String): Observable<Result<Book>>

}