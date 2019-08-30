package com.github.tak8997.coinranking.util.databinding

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.github.tak8997.coinranking.R
import com.github.tak8997.coinranking.lib.GlideApp

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat", "thumbnail", requireAll = false)
    fun setSrcCompat(imageView: ImageView, imageUrl: String?, thumbnail: Boolean = false) {
        imageUrl?.let {
            GlideApp.with(imageView)
                .load(it)
                .error(R.drawable.placeholder)
                .thumbnail(if (thumbnail) 0.4f else 1.0f)
                .placeholder(circularProgressDrawable(imageView.context))
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("android:selected")
    fun selected(view: View, selected: Boolean) {
        Log.d("MY_LOG", selected.toString())
        view.isSelected = selected
    }

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