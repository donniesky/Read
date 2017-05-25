package me.donnie.read.ui.detail.recommend

import me.donnie.read.ui.detail.BookDetailContract
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/25.
 * @description
 * @version
 */
class RecommendBooksNavigator @Inject constructor(val navigator: BookDetailContract.Navigator) : RecommendBooksContract.Navigator