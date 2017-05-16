package com.strv.transitionssample.ui.detail

import android.app.SharedElementCallback
import android.content.Intent
import android.view.View
import android.widget.ImageView

class DetailSharedElementCallback(val intent: Intent) : SharedElementCallback() {

    var photo: View? = null
    var tName: String? = null

    override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
        names?.filter { !it.startsWith("android") }?.forEach {
            names.remove(it)
            sharedElements?.remove(it)
        }

        tName?.let { name ->
            names?.add(name)

            photo?.let { view ->
                sharedElements?.put(name, view)
            }
        }
    }
}
