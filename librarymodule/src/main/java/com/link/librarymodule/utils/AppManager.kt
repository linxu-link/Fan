package com.link.librarymodule.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import java.util.*

class AppManager {

    companion object {
        val instance = Inner.appManager
    }

    private object Inner {
        val appManager = AppManager()
    }


    private val mStackActivity = Stack<Activity>()
    private val mStackFragment = Stack<Fragment>()


    fun getStackActivity(): Stack<Activity> {
        return mStackActivity
    }

    fun getStackFragment(): Stack<Fragment> {
        return mStackFragment
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        mStackActivity.add(activity)
    }

    /**
     * 移除指定的Activity
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            mStackActivity.remove(activity)
        }
    }


    /**
     * 是否有activity
     */
    fun isActivity(): Boolean {
        return !mStackActivity.isEmpty()
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        return mStackActivity.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = mStackActivity.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in mStackActivity) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = mStackActivity.size
        while (i < size) {
            if (null != mStackActivity.get(i)) {
                finishActivity(mStackActivity.get(i))
            }
            i++
        }
        mStackActivity.clear()
    }

    /**
     * 获取指定的Activity
     *
     * @author kymjs
     */
    fun getActivity(cls: Class<*>): Activity? {
        if (mStackActivity.size != 0)
            for (activity in mStackActivity) {
                return if (activity.javaClass == cls) {
                    activity
                } else {
                    null
                }
            }

        return null
    }


    /**
     * 添加Fragment到堆栈
     */
    fun addFragment(fragment: Fragment) {
        mStackFragment.add(fragment)
    }

    /**
     * 移除指定的Fragment
     */
    fun removeFragment(fragment: Fragment?) {
        if (fragment != null) {
            mStackFragment.remove(fragment)
        }
    }


    /**
     * 是否有Fragment
     */
    fun isFragment(): Boolean {
        return !mStackFragment.isEmpty()
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentFragment(): Fragment? {
        return mStackFragment.lastElement()
    }


    /**
     * 退出应用程序
     */
    fun AppExit() {
        try {
            finishAllActivity()
            // 杀死该应用进程
            //          android.os.Process.killProcess(android.os.Process.myPid());
            //            调用 System.exit(n) 实际上等效于调用：
            //            Runtime.getRuntime().exit(n)
            //            finish()是Activity的类方法，仅仅针对Activity，当调用finish()时，只是将活动推向后台，并没有立即释放内存，活动的资源并没有被清理；当调用System.exit(0)时，退出当前Activity并释放资源（内存），但是该方法不可以结束整个App如有多个Activty或者有其他组件service等不会结束。
            //            其实android的机制决定了用户无法完全退出应用，当你的application最长时间没有被用过的时候，android自身会决定将application关闭了。
            //System.exit(0);
        } catch (e: Exception) {
            mStackActivity.clear()
            e.printStackTrace()
        }

    }

}