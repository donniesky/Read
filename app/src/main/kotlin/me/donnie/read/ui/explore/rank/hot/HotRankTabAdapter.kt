package me.donnie.read.ui.explore.rank.hot

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.donnie.read.R
import me.donnie.read.ui.test.TestFragment

/**
 * @author donnieSky
 * @created_at 2017/5/15.
 * @description
 * @version
 */
class HotRankTabAdapter(val context: Context,
                        fm: FragmentManager,
                        var datas: MutableList<String>) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        return TestFragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title = ""
        when(position) {
            0 -> title = context.resources.getString(R.string.week_rank)
            1 -> title = context.resources.getString(R.string.month_rank)
            2 -> title = context.resources.getString(R.string.total_rank)
        }
        return title
    }

    override fun getCount(): Int {
        return 3
    }

}