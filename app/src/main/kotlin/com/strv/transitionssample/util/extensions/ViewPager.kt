package com.strv.transitionssample.util.extensions

import android.support.v4.view.ViewPager

fun ViewPager.onPageChanged(action: (page: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            action(position)
        }
    })
}