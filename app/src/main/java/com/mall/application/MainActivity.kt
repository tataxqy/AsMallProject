package com.mall.application

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mall.library.global.GlobalKeys
import com.mall.library.global.Mall
import com.mall.library.net.RestClient
import com.mall.library.net.callback.ISuccess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RestClient
            .builder()
            .url("http://mock.fulingjie.com/mock/api/index.php")
            .params("","")
            .success(object:ISuccess{
                override fun onSuccess(response: String) {
                    Toast.makeText(baseContext,response,Toast.LENGTH_LONG).show()
                }

            })
            .build()
            .get()

        Mall.getConfiguration<Context>(GlobalKeys.APPLICATION_CONTEXT)
    }
}
