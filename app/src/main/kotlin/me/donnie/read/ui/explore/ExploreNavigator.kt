package me.donnie.read.ui.explore

import me.donnie.read.ui.main.MainContract
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/10.
 * @description
 * @version
 */
class ExploreNavigator @Inject constructor(val navigator: MainContract.Navigator) : ExploreContract.Navigator {

    override fun navigateToRank() {
        navigator.navigateToRank()
    }

    override fun navigateToSubject() {
        navigator.navigateToSubject()
    }

}