package com.github.tak8997.coinranking.util.databinding

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.github.tak8997.coinranking.R

object BindingAdapter {

    @JvmStatic
    fun circularProgressDrawable(context: Context): CircularProgressDrawable {
        val progress = CircularProgressDrawable(context)
        progress.setColorSchemeColors(R.color.colorAccent)
        progress.strokeWidth = 5f
        progress.centerRadius = 30f
        progress.start()
        return progress
    }
}