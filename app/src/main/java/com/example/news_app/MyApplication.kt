package com.example.news_app

import android.app.Application

import com.example.data.database.MyDataBase

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()


    }


}