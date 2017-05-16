package com.strv.transitionssample.util.transitions

import android.animation.Animator
import android.animation.ValueAnimator
import android.transition.Transition
import android.transition.TransitionValues
import android.view.ViewGroup

class CustomTransition : Transition() {
    override fun captureStartValues(transitionValues: TransitionValues?) {

    }

    override fun captureEndValues(transitionValues: TransitionValues?) {

    }

    override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator {
        return ValueAnimator.ofInt(0,1)
    }
}