package com.mobile.sanitex.onboardingapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.sanitex.onboardingapp.api.NewsApi
import com.mobile.sanitex.onboardingapp.data.NewData
import com.mobile.sanitex.onboardingapp.getJsonObject
import com.mobile.sanitex.onboardingapp.toJsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewsViewModel: ViewModel() {

    private val webRequest = Retrofit
        .Builder()
        .baseUrl("https://newsapi.org/v2/")
        .build()
        .create(NewsApi::class.java)

    val news = MutableLiveData<List<NewData>>()

    fun getNews(){
        webRequest.getNews().enqueue(object: Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                news.value = ArrayList()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val temp = ArrayList<NewData>()
                if (response.isSuccessful){
                    response.body()?.string()?.toJsonObject {
                        if (getString("status") == "ok"){
                            val list = getJSONArray("articles")
                            for (i in 0 until list.length()){
                                list.getJsonObject(i){
                                    temp.add(
                                        NewData(
                                            getString("author"),
                                            getString("title"),
                                            getString("description"),
                                            getString("publishedAt"),
                                            getString("urlToImage"),
                                            getString("url")
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                news.value = temp
            }
        })
    }

}