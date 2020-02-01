package com.link.librarynetwork.cache

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object CacheManager {

    //反序列,把二进制数据转换成java object对象
    private fun toObject(data: ByteArray?): Any? {
        var bais: ByteArrayInputStream? = null
        var ois: ObjectInputStream? = null
        try {
            bais = ByteArrayInputStream(data)
            ois = ObjectInputStream(bais)
            return ois.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                bais?.close()
                ois?.close()
            } catch (ignore: Exception) {
                ignore.printStackTrace()
            }
        }
        return null
    }

    //序列化存储数据需要转换成二进制
    private fun <T> toByteArray(body: T): ByteArray {
        var baos: ByteArrayOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            baos = ByteArrayOutputStream()
            oos = ObjectOutputStream(baos)
            oos.writeObject(body)
            oos.flush()
            return baos.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                baos?.close()
                oos?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return ByteArray(0)
    }

    fun <T> save(key: String?, body: T) {
        val cache = Cache()
        cache.key = key
        cache.data = toByteArray(body)
        CacheDataBase.get().getDao().save(cache)
    }


    @JvmStatic
    fun <T> delete(key: String?, body: T) {
        val cache = Cache()
        cache.key = key!!
        cache.data = toByteArray(body)
        CacheDataBase.get().getDao().delete(cache)
    }

    @JvmStatic
    fun getCache(key: String): Any? {
        val cache = CacheDataBase.get().getDao().getCache(key)

        return if (cache.data != null) {
            toObject(cache.data)
        } else null
    }


}