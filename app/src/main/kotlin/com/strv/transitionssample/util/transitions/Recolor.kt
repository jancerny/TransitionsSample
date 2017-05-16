package com.strv.transitionssample.util.transitions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.drawable.ColorDrawable
import android.transition.Transition
import android.transition.TransitionValues
import android.view.ViewGroup
import android.widget.TextView
import com.strv.transitionssample.util.extensions.togetherAnimatorSet

class Recolor : Transition() {

    companion object {
        val PROPNAME_BACKGROUND_COLOR = "com.strv.transitionsssample:backgroundColor"
        val PROPNAME_TEXT_COLOR = "com.strv.transitionsssample:textColor"
    }

    override fun captureStartValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        if (startValues == null || endValues == null) return null

        val startBackground = startValues.values[PROPNAME_BACKGROUND_COLOR] as? Int
        val endBackground = endValues.values[PROPNAME_BACKGROUND_COLOR] as? Int
        val endBackgroundDrawable = endValues.view.background

        val startTextColor = startValues.values[PROPNAME_TEXT_COLOR] as? Int
        val endTextColor = endValues.values[PROPNAME_TEXT_COLOR] as? Int

        val backgroundAnimator = if (endBackgroundDrawable is ColorDrawable && startBackground != null && endBackground != null && startBackground != endBackground) {
            ObjectAnimator.ofArgb(endBackgroundDrawable, "color", startBackground, endBackground)
        } else null


        val textAnimator = if (endValues.view is TextView && startTextColor != null && endTextColor != null && startTextColor != endTextColor) {
            ObjectAnimator.ofArgb(endValues.view, "textColor", startTextColor, endTextColor)
        } else null

        return togetherAnimatorSet(backgroundAnimator, textAnimator)
    }

    private fun captureValues(transitionValues: TransitionValues?) {
        transitionValues?.apply {
            view?.background?.let {
                if (it is ColorDrawable) {
                    values.put(PROPNAME_BACKGROUND_COLOR, it.color)
                }
            }

            view?.apply {
                if (this is TextView) values.put(PROPNAME_TEXT_COLOR, currentTextColor)
            }
        }
    }
}