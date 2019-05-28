package com.link.librarymodule.base.mvvm.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author WJ
 * @date 2019-05-19
 *
 * 描述：
  *生命周期感知的observable，仅在订阅后发送新的更新，用于事件likenavigation和Snackbar消息。
  *这避免了事件的常见问题：在配置更改（如旋转）时，如果观察者处于活动状态，则可以发出更新。 如果存在，则此LiveData仅调用observable
  *显式调用setValue（）或call（）。
  *请注意，只有一名观察员会收到更改通知。
 */
open class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {

        }

        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }


}