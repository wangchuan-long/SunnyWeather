package com.junjun.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * 定义未来天气查询返回的数据类型
 */
// status：请求状态 result：关于结果的数组
data class DailyResponse(val status: String, val result: Result) {
    // daily：未来天气信息
    data class Result(val daily: Daily)
    // temperature：温度 skycon：天气情况 lifeIndex：其他状况
    data class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, @SerializedName("life_index") val lifeIndex: LifeIndex)
    // max：最高温度 min：最低温度
    data class Temperature(val max: Float, val min: Float)
    // value：状况 date：时间
    data class Skycon(val value: String, val date: Date)
    // coldRisk: 寒冷风险 carWashing：洗车 ultraviolet：紫外线 dressing：穿衣
    data class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing: List<LifeDescription>, val ultraviolet: List<LifeDescription>, val dressing: List<LifeDescription>)
    // desc：说明
    data class LifeDescription(val desc: String)

}