package me.donnie.read.data

import io.reactivex.Observable
import me.donnie.read.data.entity.*
import me.donnie.read.data.source.remote.RemoteBookSource
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class BookRepository @Inject constructor(val remoteBookSource: RemoteBookSource) {

    fun getRecommend(gender: String): Observable<BookList> {
        return remoteBookSource.getRecommend(gender)
    }

    fun getComplexDiscussList(start: Int): Observable<PostList> {
        return remoteBookSource.getComplexDiscussList(start)
    }

    fun getDiscussDetail(discussId: String): Observable<PostDetail> {
        return remoteBookSource.getDiscussDetail(discussId)
    }

    fun getRank(): Observable<RankList> {
        return remoteBookSource.getRank()
    }

    fun getRank(rankId: String): Observable<RankDetail> {
        return remoteBookSource.getRank(rankId)
    }

    fun getBooksList(duration: String,
                     sort: String,
                     start: Int,
                     limit: Int,
                     tag: String,
                     gender: String): Observable<BooksList> {
        return remoteBookSource.getBooksList(duration, sort, start, limit, tag, gender)
    }

    fun getBookTags(): Observable<BookTags> {
        return remoteBookSource.getBookTags()
    }

    fun getBooksDetail(booksListId: String): Observable<BooksDetail> {
        return remoteBookSource.getBooksDetail(booksListId)
    }

    fun getCategoryList(): Observable<CategoryList> {
        return remoteBookSource.getCategoryList()
    }

    fun getCategoryList2(): Observable<CategoryList> {
        return remoteBookSource.getCategoryList2()
    }

    fun getBookByCategory(gender: String,
                          type: String,
                          major: String,
                          minor: String,
                          start: Int,
                          limit: Int): Observable<BookList> {
        return remoteBookSource.getBookByCategory(gender, type, major, minor, start, limit)
    }

}