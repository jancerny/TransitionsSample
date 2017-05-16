package com.strv.transitionssample.ui.simple

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.transition.*
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.strv.transitionssample.R
import com.strv.transitionssample.databinding.ActivitySimpleBinding
import com.strv.transitionssample.ui.simple.SimpleActivity.Companion.MAGNIFY_ROTATE
import com.strv.transitionssample.ui.simple.SimpleActivity.Companion.MAGNIFY_SCALE
import com.strv.transitionssample.util.external.EaseElasticOutInterpolator
import com.strv.transitionssample.util.transitions.CustomTransition
import com.strv.transitionssample.util.transitions.Recolor
import java.util.*


class SimpleActivity : AppCompatActivity() {

    companion object {
        val MAGNIFY_SCALE = 1.5f
        val MAGNIFY_ROTATE = 30f
    }

    lateinit var binding: ActivitySimpleBinding
    var scene2 = false

    val changeTransform = ChangeTransform().apply {
        interpolator = EaseElasticOutInterpolator(100f)
        duration = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySimpleBinding>(this, R.layout.activity_simple)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun onAutoTransitionButtonClick(view: View) {
        TransitionManager.beginDelayedTransition(binding.sceneRoot)

        binding.text.toggleGone()
    }

    fun onChangeTransform(view: View) {
        TransitionManager.beginDelayedTransition(binding.sceneRoot, changeTransform)

        binding.textContent.toggleMagnify()
    }

    fun onSlideButtonClick(view: View) {
        TransitionManager.beginDelayedTransition(binding.sceneRoot, Slide())

        binding.text.visibility = View.INVISIBLE
        binding.caption.visibility = View.INVISIBLE
    }

    fun onSlideFadeButtonClick(view: View) {
        val slideFade = TransitionSet()
                .addTransition(Slide())
                .addTransition(Fade(this, null))
                .setOrdering(TransitionSet.ORDERING_TOGETHER)

        TransitionManager.beginDelayedTransition(binding.sceneRoot, slideFade)

        binding.text.toggleInvisible()
        binding.caption.toggleInvisible()
    }

    fun onSceneButtonClick(view: View) {

    }

    fun onRecolorButtonClick(view: View) {
        TransitionManager.beginDelayedTransition(binding.sceneRoot, Recolor())

        binding.text.setBackgroundColor(Random().nextColor())
        binding.text.setTextColor(Random().nextColor())
    }
}


private fun View.toggleGone() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

private fun View.toggleInvisible() {
    visibility = if (visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
}

private fun View.toggleMagnify() {
    if (scaleX > 1f) {
        scaleX = 1f
        scaleY = 1f
        rotation = 0f
    } else {
        scaleX = MAGNIFY_SCALE
        scaleY = MAGNIFY_SCALE
        rotation = MAGNIFY_ROTATE
    }
}

private fun Random.nextColor() = Color.rgb(nextInt(255), nextInt(255), nextInt(255))
