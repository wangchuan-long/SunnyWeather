package com.junjun.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 全局获取Context的方式
 */
class SunnyWeatherApplication : Application() {

    companion object {
        const val TOKEN = "3sYrSJ6YW6klbaiJ"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}