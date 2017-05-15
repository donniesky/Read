package me.donnie.read.data.entity

import java.util.*

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
data class BooksDetail(var _id: String,
                  var updated: Date,
                  var title: String,
                  var author: Author,
                  var desc: String,
                  var gender: String,
                  var created: Date,
                  var tags: List<String>,
                  var isDraft: Boolean,
                  var collectorCount: Int,
                  var books: Books) {


    class Books(var book: BookList.Book,
                var comment: String)

}