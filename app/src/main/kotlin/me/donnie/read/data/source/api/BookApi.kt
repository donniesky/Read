package me.donnie.read.data.source.api

import io.reactivex.Observable
import me.donnie.read.data.entity.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
interface BookApi {

    @GET("/book/recommend")
    fun getRecommend(@Query("gender") gender: String): Observable<BookList>

    /**
     * @param block      ramble:综合讨论区
     *                   original：原创区
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   comment-count(最多评论)
     * @param type       all
     * @param start      0
     * @param limit      20
     * @param distillate true(精品)
     */
    @GET("/post/by-block")
    fun getDiscussList(@Query("block") block: String,
                       @Query("duration") duration: String,
                       @Query("sort") sort: String,
                       @Query("type") type: String,
                       @Query("start") start: Int,
                       @Query("limit") limit: Int,
                       @Query("distillate") distillate: String): Observable<PostList>

    @GET("/post/{discussId}")
    fun getDiscussDetail(@Path("discussId") discussId: String): Observable<PostDetail>

    @GET("/ranking/gender")
    fun getRank(): Observable<RankList>

    @GET("/ranking/{rankId}")
    fun getRank(@Path("rankId") rankId: String): Observable<RankDetail>

    /**
     * 获取主题书单列表
     * 本周最热：duration=last-seven-days&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collectorCount
     *
     * @param tag    都市、古代、架空、重生、玄幻、网游
     * @param gender male、female
     * @param limit  20
     */
    @GET("/book-list")
    fun getBooksList(@Query("duration") duration: String,
                     @Query("sort") sort: String,
                     @Query("start") start: Int,
                     @Query("limit") limit: Int,
                     @Query("tag") tag: String,
                     @Query("gender") gender: String): Observable<BooksList>

    @GET("/book-list/tagType")
    fun getBookTags(): Observable<BookTags>

    @GET("/book-list/{booksListId}")
    fun getBooksDetail(@Path("booksListId") booksListId: String): Observable<BooksDetail>

    @GET("/cats/lv2/statistics")
    fun getCategoryList(): Observable<CategoryList>

    @GET("/cats/lv2")
    fun getCategoryList2(): Observable<CategoryList>

    @GET("/book/by-categories")
    fun getBookByCategory(@Query("gender") gender: String,
                          @Query("type") type: String,
                          @Query("major") major: String,
                          @Query("minor") minor: String,
                          @Query("start") start: Int,
                          @Query("limit") limit: Int): Observable<BookList>
}