package me.donnie.read.data.entity

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class BookTags(var data: List<BookTags.Tag>,
               var ok: Boolean) {

    data class Tag(var name: String,
                   var tags: List<String>)

}