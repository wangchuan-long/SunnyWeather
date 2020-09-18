package com.junjun.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 定义城市查询返回的数据模型
 */
// status：请求状态 places：关于地点的数组
data class PlaceResponse(val status: String, val places: List<Place>)

// name：城市 location：经纬度 address：详细地址
data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

// lng：经度 lat：纬度
data class Location(val lng: String, val lat: String)