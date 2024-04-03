package com.rizahanmiy.newsapp.presentation.ui.webview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.webkit.WebChromeClient
import com.rizahanmiy.newsapp.R
import com.rizahanmiy.newsapp.data.base.BaseActivity
import com.rizahanmiy.newsapp.utils.common.overrideWebView
import com.rizahanmiy.newsapp.utils.constants.AppConstants.KeyIntents.KEY_URL
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.layout_toolbar_level2.*

class WebViewActivity : BaseActivity() {

    companion object {
        fun start(context: Context, url: String) {
            val starter = Intent(context, WebViewActivity::class.java).apply {
                putExtra(KEY_URL, url)
            }
            context.startActivity(starter)
        }
    }
    var url : String = ""

    override val layout: Int = R.layout.activity_web_view

    override fun onPreparation() {
    }

    override fun onIntent() {
        url = intent.getStringExtra(KEY_URL).orEmpty()
        Log.d("TAG", "url : $url")
        loadWeb(url)
    }

    override fun onUi() {
        tvTitle.text = getString(R.string.app_name)
    }

    override fun onAction() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }
    override fun onObserver() {
    }

    private fun loadWeb(url : String){
        viewWeb.webChromeClient = WebChromeClient()
        viewWeb.loadUrl(url)
        overrideWebView(viewWeb = viewWeb, this@WebViewActivity)
    }
}