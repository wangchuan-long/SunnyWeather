package com.junjun.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 定义实时天气查询返回的数据模型
 */
// status：请求状态 result：关于结果的数组
data class RealtimeResponse(val status: String, val result: Result) {
    // realtime：实时天气信息
    data class Result(val realtime: Realtime)
    // skycon：天气情况 temperature：温度 airQuality：空气质量
    data class Realtime(val skycon: String, val temperature: Float, @SerializedName("air_quality") val airQuality: AirQuality)
    // aqi：空气质量指数
    data class AirQuality(val aqi: AQI)
    // chn：空气质量指数具体数值
    data class AQI(val chn: Float)

}