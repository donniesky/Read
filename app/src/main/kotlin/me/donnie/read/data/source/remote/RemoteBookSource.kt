package me.donnie.read.data.source.remote

import io.reactivex.Observable
import me.donnie.read.data.entity.*
import me.donnie.read.data.source.api.BookApi
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class RemoteBookSource @Inject constructor(val bookApi: BookApi) {

    fun getRecommend(gender: String): Observable<BookList> {
        return bookApi.getRecommend(gender)
    }

    fun getComplexDiscussList(start: Int): Observable<PostList> {
        return bookApi.getDiscussList("ramble", "all", "updated", "all", start, 20, "true")
    }

    fun getDiscussDetail(discussId: String): Observable<PostDetail> {
        return bookApi.getDiscussDetail(discussId)
    }

    fun getRank(): Observable<RankList> {
        return bookApi.getRank()
    }

    fun getRank(rankId: String): Observable<RankDetail> {
        return bookApi.getRank(rankId)
    }

    fun getBooksList(duration: String,
                     sort: String,
                     start: Int,
                     limit: Int,
                     tag: String,
                     gender: String): Observable<BooksList> {
        return bookApi.getBooksList(duration, sort, start, limit, tag, gender)
    }

    fun getBookTags(): Observable<BookTags> {
        return bookApi.getBookTags()
    }

    fun getBooksDetail(booksListId: String): Observable<BooksDetail> {
        return bookApi.getBooksDetail(booksListId)
    }

    fun getCategoryList(): Observable<CategoryList> {
        return bookApi.getCategoryList()
    }

    fun getCategoryList2(): Observable<CategoryList> {
        return bookApi.getCategoryList2()
    }

    fun getBookByCategory(gender: String,
                          type: String,
                          major: String,
                          minor: String,
                          start: Int,
                          limit: Int): Observable<BookList> {
        return bookApi.getBookByCategory(gender, type, major, minor, start, limit)
    }
}