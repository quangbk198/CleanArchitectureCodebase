package com.example.dictionaryapp.di

import android.content.Context
import com.example.dictionaryapp.BuildConfig
import com.quangnh.data.datasource.remote.ApplicationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by quangnh
 * Date: 1/2/2023
 * Time: 10:30 PM
 * Project DictionaryApp
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providerHttpCacheSize(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 //10MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun providerAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()

            requestBuilder.addHeader("Accept", "application/json")
            requestBuilder.addHeader("app_id", "36a9f1be")
            requestBuilder.addHeader("app_key", "6fbb447d8defd90e8bf404663b4edc03")

            try {
                return@Interceptor chain.proceed(requestBuilder.build())
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    @Singleton
    @Provides
    fun providerOkHttpClient(
        httpCache: Cache,
        interceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(httpCache)

        val logging = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.HEADERS)
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }
        client.apply {
            connectTimeout(30.toLong(), TimeUnit.SECONDS)
            readTimeout(30.toLong(), TimeUnit.SECONDS)
            writeTimeout(30.toLong(), TimeUnit.SECONDS)
            addInterceptor(interceptor)
            addNetworkInterceptor(interceptor)
            addInterceptor(logging)
        }

        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://od-api.oxforddictionaries.com:443/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun providerManagerMallRetrofit(
        retrofit: Retrofit.Builder
    ): ApplicationApi {
        return retrofit.build().create(ApplicationApi::class.java)
    }
}