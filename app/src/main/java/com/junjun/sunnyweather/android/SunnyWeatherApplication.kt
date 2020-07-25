package com.junjun.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 全局获取Context的方式
 */
class SunnyWeatherApplication : Application() {

    companion object {
        const val TOKEN = "填入申请到的令牌值"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}