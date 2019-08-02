package com.link.component_update.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.link.component_update.R
import com.link.component_update.data.entity.Update
import com.link.librarymodule.base.BaseActivity
import kotlinx.android.synthetic.main.update_fragment.*

/**
 * @author WJ
 * @date 2019-07-17
 *
 * 描述：
 */
class UpdateActivity : BaseActivity() {

    private lateinit var mUpdateEntity: Update

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUpdateEntity = intent.getSerializableExtra(Update::class.java.canonicalName) as Update
        setContentView(R.layout.update_fragment)

        version_name.text = mUpdateEntity.versionCode
        content.text = mUpdateEntity.updateContent

        if (mUpdateEntity.forceUpdate) {
            cancel.visibility = View.INVISIBLE
        }

        cancel.setOnClickListener {
            onBackPressed()
        }
        confirm.setOnClickListener {
            downloadApk()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return !mUpdateEntity.forceUpdate && super.onKeyDown(keyCode, event)

    }

    /**
     * 下载apk文件
     */
    private fun downloadApk() {
        try {
            val uri = Uri.parse(mUpdateEntity.apk.fileUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
