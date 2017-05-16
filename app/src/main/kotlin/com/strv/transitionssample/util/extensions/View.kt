package com.strv.transitionssample.util.extensions

import android.view.View
import android.view.ViewTreeObserver

fun <T : View> T.onPreDraw(action: T.() -> Boolean) {
    val listener = object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            return action()
        }
    }

    viewTreeObserver.addOnPreDrawListener(listener)
}