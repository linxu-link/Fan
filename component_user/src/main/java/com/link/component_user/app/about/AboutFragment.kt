package com.link.component_user.app.about


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.link.component_user.R
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.utils.CommonUtil
import kotlinx.android.synthetic.main.user_fragment_about.*
/**
 * @author WJ
 * @date 2019-07-21
 *
 * 描述：关于fan
 */
class AboutFragment(override var layoutId: Int=R.layout.user_fragment_about) : BaseFragment() {


    companion object {
            @JvmStatic
            fun newInstance() =
                AboutFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title=mRootView!!.findViewById<TextView>(R.id.title)
        val back=mRootView!!.findViewById<ImageView>(R.id.back)
        title.text="我的收藏"
        back.setOnClickListener {
            findNavController().navigateUp()
        }

        version.text="Ver:${CommonUtil.getPackageInfo().versionName}"
        jianshu.setOnClickListener {
            goWebView("https://www.jianshu.com/u/70472b2a1af5")
        }
        github.setOnClickListener {
            goWebView("https://github.com/linux-link/PetMVVMApp")
        }
    }


    private fun goWebView(url:String) {
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}
