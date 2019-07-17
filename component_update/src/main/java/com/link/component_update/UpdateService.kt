package com.link.component_update

import android.content.Context
import com.link.component_update.app.UpdateService
import com.link.librarycomponent.service.update.IUpdateService

class UpdateService : IUpdateService {

    override fun startUpdate(context: Context) {
        UpdateService.startService(context)
    }
}