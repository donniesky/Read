package me.donnie.read.common.injection.module

import android.content.Context
import com.github.pwittchen.prefser.library.Prefser
import dagger.Module
import dagger.Provides
import me.donnie.read.common.injection.qualifier.ApplicationContext
import me.donnie.read.common.utils.NetWorkUtil
import me.donnie.read.common.utils.ToastUtil
import javax.inject.Singleton

/**
 * @author donnieSky
 * @created_at 2017/5/8.
 * @description
 * @version
 */
@Module
class UtilsModule {

    @Singleton
    @Provides
    fun provideNetWorkUtil(@ApplicationContext context: Context): NetWorkUtil {
        return NetWorkUtil(context)
    }

    @Singleton
    @Provides
    fun provideToastUtil(@ApplicationContext context: Context): ToastUtil {
        return ToastUtil(context)
    }

    @Singleton
    @Provides
    fun providePrefser(@ApplicationContext context: Context): Prefser {
        return Prefser(context)
    }

}