package com.strv.transitionssample.util.extensions

import android.graphics.Bitmap

val Bitmap.aspectRatio: Double get() = if(height > 0) width.toDouble() / height.toDouble() else 0.0