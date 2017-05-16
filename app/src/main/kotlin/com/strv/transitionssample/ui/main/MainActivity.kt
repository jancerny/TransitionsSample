package com.strv.transitionssample.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.strv.transitionssample.R
import com.strv.transitionssample.ui.list.ListActivity
import com.strv.transitionssample.ui.scene.SceneActivity
import com.strv.transitionssample.ui.simple.SimpleActivity
import com.strv.transitionssample.util.extensions.onTransitionEnd

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onAllTogetherButtonClick(view: View) {
        startActivity(Intent(this, ListActivity::class.java))
    }

    fun onSimpleButtonClick(view: View) {
        startActivity(Intent(this, SimpleActivity::class.java))
    }

    fun onSceneButtonClick(view: View) {
        startActivity(Intent(this, SceneActivity::class.java))
    }
}