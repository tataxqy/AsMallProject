package com.mall.library.global

import android.content.Context
import android.provider.Settings
import com.blankj.utilcode.util.Utils
import com.mall.library.util.storage.MemoryStore

object  Mall {

    val configurator:Configurator
        get()= Configurator.instance

    fun init(context: Context): Configurator {
        MemoryStore.instance
            .addData(GlobalKeys.APPLICATION_CONTEXT,context.applicationContext)
        Utils.init(context)
        return Configurator.instance
    }



    fun<T> getConfiguration(key:String):T{
        return configurator.getConfiguration(key)

    }
    fun<T> getConfiguration(key:Enum<GlobalKeys>):T{

        return getConfiguration(key.name)

    }

}