package com.anangkur.kotlinexpertsubmission.data.remote

import com.anangkur.kotlinexpertsubmission.BuildConfig.baseUrl
import com.anangkur.kotlinexpertsubmission.data.model.ResponseLeagueDetail
import com.anangkur.kotlinexpertsubmission.data.model.ResponseMatch
import com.anangkur.kotlinexpertsubmission.data.model.ResponseSearchMatch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("lookupleague.php")
    suspend fun getLeagueDetail(@Query("id") id: String): Response<ResponseLeagueDetail>

    @GET("eventspastleague.php")
    suspend fun getPrevMatch(@Query("id") id: String): Response<ResponseMatch>

    @GET("eventsnextleague.php")
    suspend fun getNextMatch(@Query("id") id: String): Response<ResponseMatch>

    @GET("lookupevent.php")
    suspend fun getDetailMatch(@Query("id") id: String): Response<ResponseMatch>

    @GET("searchevents.php")
    suspend fun getSearchMatch(@Query("e") e: String): Response<ResponseSearchMatch>

    companion object Factory{
        val getApiService: ApiService by lazy {

            val mClient =
                OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()

            val mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build()

            mRetrofit.create(ApiService::class.java)
        }
    }
}