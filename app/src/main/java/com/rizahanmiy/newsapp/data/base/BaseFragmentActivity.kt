package com.rizahanmiy.newsapp.data.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

abstract class BaseFragmentActivity : FragmentActivity() {

    abstract val layout:Int

    abstract fun onPreparation()
    abstract fun onIntent()
    abstract fun onUi()
    abstract fun onAction()
    abstract fun onObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        onPreparation()
        onIntent()
        onUi()
        onAction()
        onObserver()
    }

}