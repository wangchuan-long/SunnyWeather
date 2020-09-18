package com.junjun.sunnyweather.android.logic.network

import com.junjun.sunnyweather.android.SunnyWeatherApplication
import com.junjun.sunnyweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 用于访问彩云天气城市搜索API的Retrofit接口
 */
interface PlaceService {

    // 返回的JSON数据自动解析成PlaceResponse对象
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}