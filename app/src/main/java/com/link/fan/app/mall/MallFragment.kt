package com.link.fan.app.mall


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.link.fan.R
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.FragmentMallBinding
import com.link.fan.utils.JavaScriptInterface
import com.link.fan.widgets.webview.X5WebView
import com.link.librarymodule.constant.SHOPPING_WEB_URL
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.export.external.interfaces.WebResourceResponse
import kotlinx.android.synthetic.main.fragment_mall.*

/**
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-10:55
 *  email:wujia0916@thundersoft.com
 *  description:
 */
class MallFragment : Fragment() {

    private var mX5WebView: X5WebView? = null

    private var mJavaScriptInterface: JavaScriptInterface? = null

    private val viewModel: MallViewModel by viewModels {
        InjectorUtils.loginViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMallBinding>(inflater, R.layout.fragment_mall, container, false).apply {
            viewModel = this@MallFragment.viewModel
            lifecycleOwner = viewLifecycleOwner

            mX5WebView = X5WebView(requireContext(), null)
            content?.addView(mX5WebView,
                    ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            mJavaScriptInterface = JavaScriptInterface(requireContext())
            mX5WebView?.addJavascriptInterface(mJavaScriptInterface, "androidJSBridge")
            mX5WebView?.setOnWebViewListener(object : X5WebView.OnWebViewListener {

                override fun shouldInterceptRequest(request: WebResourceRequest?, url: String?): WebResourceResponse? {
                    return null
                }

                override fun onPageFinished(url: String?) {

                }

                override fun onProgressChanged(progress: Int) {
                    progress_bar.progress = progress
                    if (progress == 100) {
                        progress_bar.visibility = View.GONE
                    }
                }

            })
        }
        subscribeUi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRemoteData()
    }

    private fun subscribeUi() {
        viewModel.goodsData.observe(this, Observer {
            mJavaScriptInterface?.goodsData = it
            mX5WebView?.loadUrl(SHOPPING_WEB_URL)
        })

        viewModel.secondData.observe(this, Observer {
            mJavaScriptInterface?.secondsData = it
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MallFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
