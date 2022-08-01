package com.choirunnisa.fidac_choirunnisa_test.di

import android.app.Application
import android.content.Context
import com.choirunnisa.fidac_choirunnisa_test.network.FindacApi
import com.choirunnisa.fidac_choirunnisa_test.network.FindacService
import com.choirunnisa.fidac_choirunnisa_test.network.HttpFindacService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule{
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor{
            val request = it.request()
            val newUrl = request.url().newBuilder()
                .host("https://app.minjem.biz.id/")
                .build()
            it.proceed(request.newBuilder().url(newUrl).build())
        }.build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://localhost/")
        .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper())).build()


    @Singleton
    @Provides
    fun provideFindacApi(retrofit: Retrofit): FindacApi = retrofit.create(FindacApi::class.java)

    @Provides
    fun provideFindacService(findacApi: FindacApi): FindacService =
        HttpFindacService(findacApi)
}
