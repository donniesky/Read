package me.donnie.read.ui.detail.review

import me.donnie.read.ui.detail.BookDetailContract
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/23.
 * @description
 * @version
 */
class BestReviewNavigator @Inject constructor(var navigator: BookDetailContract.Navigator) : BestReviewContract.Navigator