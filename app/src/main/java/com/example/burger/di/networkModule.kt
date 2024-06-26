package com.example.burger.di

import android.content.Context
import com.google.gson.GsonBuilder
import io.github.brunogabriel.mockpinterceptor.MockPInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.android.BuildConfig.DEBUG

private const val BASE_URL = "https://burgers-hub.p.rapidapi.com/"
private const val HEADER_KEY_ACCEPT = "X-RapidAPI-Key"
private const val HEADER_VALUE_JSON = "2fd659e119msh090ab004127ec46p1c4ccdjsn27c15565d2ba"

fun createNetworkModule(context: Context) = module {

    val mockpInterceptor = MockPInterceptor
        .Builder(context)
        .addDelayInMillis(2_000L, 5_000L)
        .build()

    factory { GsonBuilder().setLenient().create() }

    factory {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        name = HEADER_KEY_ACCEPT,
                        value= HEADER_VALUE_JSON
                    )
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(mockpInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
}