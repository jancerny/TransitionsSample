//@file:InverseBindingMethods(
//        InverseBindingMethod(type = Spinner::class, attribute = "selectedItem", method = "selectedItem")
//)

package com.strv.transitionssample.util

import android.databinding.*
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.view.ViewPager
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import com.bumptech.glide.BitmapRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.lang.Exception

object BindingAdapters {
    @BindingAdapter("loadUrl", "placeholder", "onLoadUrl", requireAll = false)
    @JvmStatic fun loadUrl(imageView: ImageView, url: String, placeholder: Drawable? = null, onLoadUrl: Runnable? = null) {
        var urlLoaded = false

        Glide.with(imageView.context)
                .load(url)
                .asBitmap()
                .dontAnimate()
                .placeholder(placeholder)
                .onLoadFinished(onLoadUrl)
                .into(imageView)


    }

    @BindingAdapter("currentItem")
    @JvmStatic fun setCurrentItem(viewPager: ViewPager, index: Int) {
        viewPager.setCurrentItem(index, false)
    }


    @BindingAdapter("selectedItem")
    @JvmStatic fun <T> setSelectedItem(spinner: Spinner, item: ObservableField<T>) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                item.set(spinner.getItemAtPosition(position) as T)
            }

        }
        var index = 0
        (0..(spinner.count - 1)).forEach { i ->
            val itemAtPos = spinner.getItemAtPosition(i)
            if (item.get() == itemAtPos) {
                index = i
            }
        }
        spinner.setSelection(index)
    }

    @InverseBindingAdapter(attribute = "selectedItem", event = "selectedItemAttrChanged")
    @JvmStatic fun <T> getSelectedItem(spinner: Spinner): T {
        return spinner.selectedItem as? T ?: throw IllegalStateException("Cannot cast Spinner's selected item")
    }

    @BindingAdapter("fillViewPort")
    @JvmStatic fun setFillViewPort(nestedScrollView: NestedScrollView, fillViewPort: Boolean) {
        nestedScrollView.isFillViewport = fillViewPort
    }
}

fun BitmapRequestBuilder<String, Bitmap>.onLoadFinished(action: Runnable?) = action?.let {
    listener(object : RequestListener<String, Bitmap> {
        override fun onException(e: Exception?, s: String?, t: Target<Bitmap>?, f: Boolean) = false
        override fun onResourceReady(d: Bitmap, s: String, t: Target<Bitmap>?, c: Boolean, f: Boolean) = false.apply {
            action.run()
        }
    })
} ?: this
