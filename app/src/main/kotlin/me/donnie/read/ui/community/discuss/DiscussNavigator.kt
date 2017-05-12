package me.donnie.read.ui.community.discuss

import com.github.pwittchen.prefser.library.Prefser
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/12.
 * @description
 * @version
 */
class DiscussNavigator @Inject constructor(val activity: DiscussActivity) : DiscussContract.Navigator {

    @Inject
    lateinit var prefser: Prefser

}