package me.donnie.read.ui.recommend

import me.donnie.read.data.entity.BookList
import me.donnie.read.ui.main.MainContract
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class RecommendNavigator @Inject constructor(val navigator: MainContract.Navigator) : RecommendContract.Navigator {

    override fun navigateToDetail(book: BookList.Book) {
        navigator.navigateToDetail(book)
    }

}