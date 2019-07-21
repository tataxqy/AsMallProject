package com.mall.library.net.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestCallback (
    private val request:IRequest?,
    private val success:ISuccess?,
    private val failue:IFailure?,
    private val error:IError?,
    private val complete:IComplete?
): Callback<String> {
    override fun onFailure(call: Call<String>, t: Throwable) {
        if(failue!=null){
            failue.onFailure()

        }
        if(request!=null)
        {
            request.onRequestEnd()
        }
    }

    override fun onResponse(call: Call<String>, response: Response<String>) {
        if(response.isSuccessful){
            if(call.isExecuted){
                if(success!=null){
                    if(response.body()!=null){
                        success.onSuccess(response.body()!!)
                    }
                }
            }
        }else{
                error?.onError(response.code(),response.message())
        }
    }



}