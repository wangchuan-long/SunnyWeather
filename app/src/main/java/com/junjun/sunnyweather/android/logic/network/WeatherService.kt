package com.junjun.sunnyweather.android.logic.network

import com.junjun.sunnyweather.android.SunnyWeatherApplication
import com.junjun.sunnyweather.android.logic.model.DailyResponse
import com.junjun.sunnyweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 定义一个用于访问天气信息API的接口
 */
interface WeatherService {
    // 获取实时天气信息
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>
    // 获取未来天气信息
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}