package com.strv.transitionssample.ui.detail

import android.view.View
import android.view.ViewGroup
import com.strv.transitionssample.R
import com.strv.transitionssample.data.model.Photo
import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter

class DetailAdapter(val sharedElementCallback: DetailSharedElementCallback) : BindingViewPagerAdapter<Photo>() {
override fun setPrimaryItem(container: ViewGroup?, position: Int, rootView: Any?) {
        if (rootView is View) {
            sharedElementCallback.photo = rootView.findViewById(R.id.image)
            sharedElementCallback.tName = sharedElementCallback.photo!!.transitionName
        }
    }
}