package com.mall.library.global

import android.os.Handler
import com.mall.library.util.storage.MemoryStore
import java.lang.RuntimeException

/**
 *全局控制类
 */

class Configurator private constructor(){

    private object Holder{
        internal val INSTANCE =Configurator()

    }

    companion object {
        //这里获取到全局的存储容器
        private val mStore:MemoryStore= MemoryStore.instance
        //Handler 需要反复使用，不妨提前创建

        private val mHandler= Handler()


        internal val instance:Configurator
            get()=Holder.INSTANCE
    }

    init {
        //加一个标签，判断配置是否完成
        mStore.addData(GlobalKeys.IS_CONFIGURATOR_READY,false)
        mStore.addData(GlobalKeys.HANDLER, mHandler)

    }

    /**
     * 访问服务器端API设置
     */
    fun withApiHost(host:String):Configurator{

        mStore.addData(GlobalKeys.API_HOST,host)
        return this

    }

    //配置完成
    fun configure()
    {
        mStore.addData(GlobalKeys.IS_CONFIGURATOR_READY,true)
        //下面可以做一些回收动作
    }


    private fun checkConfiguration()
    {
        val isReady=mStore.getData<Boolean>(GlobalKeys.IS_CONFIGURATOR_READY)
        if(!isReady){
            throw RuntimeException("config is not ready!")

        }
    }
    fun<T> getConfiguration(key:String):T{
        return mStore.getData(key)
    }

    fun<T> getConfiguration(key:Enum<*>):T{
        checkConfiguration()
        return getConfiguration(key.name)
    }


    



}