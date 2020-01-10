package com.mobile.sanitex.onboardingapp.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything?apiKey=57a79eac5a8f44efa2bd3408139b83f3")
    fun getNews(
        @Query("q") search: String = "kotlin"
    ): Call<ResponseBody>

}