package com.strv.transitionssample.util.extensions

import android.animation.Animator
import android.animation.AnimatorSet

fun Animator.sequentialWith(animator: Animator): AnimatorSet = AnimatorSet().apply { playSequentially(this, animator) }
fun Animator.togetherWith(animator: Animator): AnimatorSet = AnimatorSet().apply { playTogether(this, animator) }

fun sequentialAnimatorSet(vararg animator: Animator?): AnimatorSet = AnimatorSet().apply { playSequentially(animator.filterNotNull()) }
fun togetherAnimatorSet(vararg animator: Animator?): AnimatorSet = AnimatorSet().apply { playTogether(animator.filterNotNull()) }