package com.link.general_daemon

import android.annotation.TargetApi
import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build

/**
 * @author WJ
 * @date 2019-08-21
 *
 * 描述：
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class JobSchedulerService : JobService() {

    override fun onStopJob(params: JobParameters?): Boolean {
        return if (DaemonEnv.sInitialized) {
            DaemonEnv.startServiceMayBind(DaemonEnv.sServiceClass!!)
            true
        } else {
            false
        }
    }

    override fun onStartJob(params: JobParameters?): Boolean {

        return false

    }
}