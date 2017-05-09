package me.donnie.read.common.injection.module

import dagger.Module
import dagger.Provides
import me.donnie.read.data.source.api.BookApi
import retrofit2.Retrofit

/**
 * @author donnieSky
 * @created_at 2017/5/9.
 * @description
 * @version
 */
@Module
class ApiModule {

    @Provides
    fun provideBookApi(retrofit: Retrofit): BookApi {
        return retrofit.create(BookApi::class.java)
    }

}