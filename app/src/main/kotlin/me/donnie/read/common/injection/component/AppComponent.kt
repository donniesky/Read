package me.donnie.read.common.injection.component

import android.content.Context
import com.github.pwittchen.prefser.library.Prefser
import dagger.Component
import me.donnie.read.common.injection.module.AppModule
import me.donnie.read.common.injection.module.ProviderModule
import me.donnie.read.common.injection.module.UtilsModule
import me.donnie.read.common.injection.qualifier.ApplicationContext
import me.donnie.read.common.utils.NetWorkUtil
import me.donnie.read.common.utils.ToastUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, UtilsModule::class, ProviderModule::class))
interface AppComponent {

    @ApplicationContext
    fun getApplicationContext(): Context

    fun netWork(): NetWorkUtil

    fun toast(): ToastUtil

    fun prefser(): Prefser

    fun retrofit(): Retrofit

    fun client(): OkHttpClient

}