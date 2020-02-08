package com.link.fan.app.capture

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.link.fan.R
import java.security.AccessControlContext

class CaptureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture)
    }

    companion object {

        const val REQ_CAPTURE = 0
        const val RESULT_FILE_PATH = ""
        const val RESULT_FILE_WIDTH = ""
        const val RESULT_FILE_HEIGHT = ""
        const val RESULT_FILE_TYPE = ""

        @JvmStatic
        fun startActivityForResult(context: Context) {
            val intent = Intent(context, CaptureActivity::class.java)
            context.startActivity(intent)
        }

    }
}
