package me.donnie.read.ui.explore.subject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.util.DiffUtil
import kotlinx.android.synthetic.main.activity_subject.*
import me.donnie.read.R
import me.donnie.read.common.base.BaseActivity
import me.donnie.read.common.injection.component.AppComponent
import me.donnie.read.data.entity.Question
import javax.inject.Inject




/**
 * @author donnieSky
 * @created_at 2017/5/18.
 * @description
 * @version
 */
class SubjectActivity : BaseActivity(), SubjectContract.View {

    @Inject
    lateinit var navigator: SubjectContract.Navigator

    @Inject
    lateinit var presenter: SubjectContract.Presenter

    private var adapter: SubjectAdapter? = null

    var subjectComponent: SubjectComponent? = null

    companion object {
        @JvmStatic fun getCallingIntent(context: Context): Intent {
            val callingIntent = Intent(context, SubjectActivity::class.java)
            return callingIntent
        }
    }

    override fun setupComponent(component: AppComponent?) {
        subjectComponent = DaggerSubjectComponent.builder()
                .appComponent(component!!)
                .subjectModule(SubjectModule(this))
                .build()
        subjectComponent!!.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_subject
    }

    override fun initView(savedInstanceState: Bundle?) {
        presenter.attachView(this)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun initData() {
    }

    private fun calculateDiff(oldList: List<Question>, newList: List<Question>) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }
        }).dispatchUpdatesTo(adapter)
    }


    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

}