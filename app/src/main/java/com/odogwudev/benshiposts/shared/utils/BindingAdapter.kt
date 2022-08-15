package com.odogwudev.benshiposts.shared.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, any: Any?) {
    any?.let {
        view.load(it) {
            crossfade(true)
        }
    }
}
