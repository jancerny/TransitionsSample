package com.strv.transitionssample.util.extensions

import android.app.SharedElementCallback
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup

fun View.inTransition(action: View.() -> Unit) {
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup)
    action(this)
}


fun Transition.onTransitionEnd(action: Transition.() -> Unit) {

    val listener = object : Transition.TransitionListener {
        override fun onTransitionEnd(transition: Transition?) {
            transition?.action()
        }

        override fun onTransitionResume(transition: Transition?) {}

        override fun onTransitionPause(transition: Transition?) {}

        override fun onTransitionCancel(transition: Transition?) {}

        override fun onTransitionStart(transition: Transition?) {}
    }

    addListener(listener)
}
