package me.donnie.read.ui.main

import com.github.pwittchen.prefser.library.Prefser
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class MainNavigator @Inject constructor(val activity: MainActivity) : MainContract.Navigator {

    @Inject
    lateinit var prefser: Prefser

    override fun navigateToRecommend() {

    }

}