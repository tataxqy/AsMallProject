package com.mall.library.util.storage

@Suppress("UNCHECKED_CAST")
class MemoryStore private constructor(){
    /**
     * 线程安全的单例模式
     */
    private object Holder{
        internal val INSTANCE =MemoryStore()


    }

    companion object{
        val instance:MemoryStore
           get() =Holder.INSTANCE
    }

    private val mDataMap=HashMap<String,Any>()

    fun addData(key:String,value:Any):MemoryStore{
        mDataMap[key]=value
        return this

    }

    fun <T> getData(key:String):T{
        return mDataMap[key] as T
    }



    fun addData(key:Enum<*>,value:Any):MemoryStore{
        addData(key.name,value)
        return this
    }

    fun <T> getData(key:Enum<*>):T{
        return getData(key.name)
    }
}