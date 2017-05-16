package com.strv.transitionssample.ui.scene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.transition.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.strv.transitionssample.R
import com.strv.transitionssample.util.transitions.CustomTransition


class SceneActivity : AppCompatActivity() {

    var sceneId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene)
    }

    fun onNextClick(view: View) {
        val sceneRoot = findViewById(R.id.scene_root) as ViewGroup
        val nextSceneLayout = when (sceneId) {
            0 -> R.layout.scene2
            else -> R.layout.scene1
        }
        val nextScene = Scene.getSceneForLayout(sceneRoot, nextSceneLayout, this).apply {
            setEnterAction { toggleSceneId() }
        }

        val shared = TransitionInflater.from(this)
                .inflateTransition(android.R.transition.move)
                .addTarget("image")

        val set = TransitionSet()
                .addTransition(shared)
                .addTransition(Slide())

        TransitionManager.go(nextScene, set)
    }

    private fun toggleSceneId() {
        if (sceneId == 0) sceneId = 1 else sceneId = 0
    }
}
