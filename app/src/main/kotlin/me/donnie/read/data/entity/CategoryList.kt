package me.donnie.read.data.entity

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class CategoryList(var male: List<Category>,
                   var female: List<Category>,
                   var press: List<Category>,
                   var ok: Boolean) {

    data class Category(var name: String,
                        var bookCount: Int,
                        var major: String,
                        var mins: List<String>)

}