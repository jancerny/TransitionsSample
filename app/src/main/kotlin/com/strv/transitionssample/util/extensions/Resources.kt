package com.strv.transitionssample.util.extensions

import android.content.res.Resources


fun Resources.getLabel(obj: Any) : String = getString(getIdentifier(obj.toString(), null, null))