package com.rizahanmiy.newsapp.utils.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.replaceFragment(
    frameId: Int,
    fragment: Fragment
) {
    supportFragmentManager.beginTransaction()
        .replace(frameId, fragment)
        .commit()
}