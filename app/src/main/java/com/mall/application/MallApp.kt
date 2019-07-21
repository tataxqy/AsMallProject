package com.mall.application

import android.app.Application
import com.mall.library.global.Mall

class MallApp:Application()
{

    override fun onCreate() {
        super.onCreate()
        Mall.init(this)
            .withApiHost("http://mock.fulingjie.com/mock/api/")
            .configure()

    }
}