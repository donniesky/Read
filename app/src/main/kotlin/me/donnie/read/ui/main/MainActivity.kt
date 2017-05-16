package me.donnie.read.ui.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import me.donnie.read.R
import me.donnie.read.common.base.BaseActivity
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.common.utils.disableShiftingMode
import me.donnie.read.common.utils.setMeizuDarkMode
import me.donnie.read.common.utils.setXiaomiDarkMode
import me.donnie.read.ui.community.CommunityFragment
import me.donnie.read.ui.explore.ExploreFragment
import me.donnie.read.ui.recommend.RecommendFragment
import me.donnie.read.ui.test.TestFragment
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
class MainActivity : BaseActivity(), MainContract.View {

    private var recommendFragment: Fragment? = null

    private var communityFragment: Fragment? = null

    private var exploreFragment: Fragment? = null

    private var testFragment: Fragment? = null

    @Inject
    lateinit var navigator: MainContract.Navigator

    @Inject
    lateinit var presenter: MainContract.Presenter

    var mainComponent: MainComponent? = null

    companion object {
        @JvmStatic fun getCallingIntent(context: Context): Intent {
            val callingIntent = Intent(context, MainActivity::class.java)
            return callingIntent
        }
    }

    override fun setupComponent(component: AppComponent?) {
        mainComponent = DaggerMainComponent.builder()
                .appComponent(component)
                .mainModule(MainModule(this))
                .build()

        mainComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        presenter.attachView(this)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else {
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }

        val brand = Build.BRAND
        if (brand.contains("Xiaomi")) {
            setXiaomiDarkMode()//小米
        } else if (brand.contains("Meizu")) {
            setMeizuDarkMode()//魅族
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }


        setSupportActionBar(toolbar)
        navigation!!.disableShiftingMode()
        navigation!!.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            item.isChecked = true
            when(item.itemId) {
                R.id.navigation_home -> switchFragment(recommendFragment, RecommendFragment.TAG)
                R.id.navigation_dashboard -> switchFragment(communityFragment, CommunityFragment.TAG)
                R.id.navigation_notifications -> switchFragment(exploreFragment, ExploreFragment.TAG)
                else -> switchFragment(testFragment, TestFragment.TAG)
            }

            return@OnNavigationItemSelectedListener false
        })

        initFragments(savedInstanceState)
    }

    private fun initFragments(savedInstanceState: Bundle?) {
        val manager = supportFragmentManager
        recommendFragment = manager.findFragmentByTag(RecommendFragment.TAG)
        communityFragment = manager.findFragmentByTag(CommunityFragment.TAG)
        exploreFragment = manager.findFragmentByTag(ExploreFragment.TAG)
        testFragment = manager.findFragmentByTag(TestFragment.TAG)

        if (recommendFragment == null) {
            recommendFragment = RecommendFragment.newInstance()
        }
        if (communityFragment == null) {
            communityFragment = CommunityFragment.newInstance()
        }
        if (exploreFragment == null) {
            exploreFragment = ExploreFragment.newInstance()
        }
        if (testFragment == null) {
            testFragment = TestFragment.newInstance()
        }
        if (savedInstanceState == null) {
            switchFragment(recommendFragment, RecommendFragment.TAG)
        }
    }

    private fun switchFragment(fragment: Fragment?, tag: String?): Boolean {
        if (fragment!!.isAdded) {
            return false
        }

        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        val currentFragment = manager.findFragmentById(R.id.container)
        if (currentFragment != null) {
            ft.detach(currentFragment)
        }
        if (fragment.isDetached) {
            ft.attach(fragment)
        } else {
            ft.add(R.id.container, fragment, tag)
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
        manager.executePendingTransactions()
        return true
    }

    override fun initData() {

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
