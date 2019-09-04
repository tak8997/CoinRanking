package com.github.tak8997.coinranking.util.databinding

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.github.tak8997.coinranking.R
import com.github.tak8997.coinranking.lib.GlideApp
import com.github.tak8997.coinranking.lib.SvgSoftwareLayerSetter


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat", "thumbnail", requireAll = false)
    fun setSrcCompat(imageView: ImageView, imageUrl: String?, thumbnail: Boolean = false) {
        imageUrl?.let {
            val requestBuilder = GlideApp.with(imageView)
                .`as`(PictureDrawable::class.java)
                .placeholder(circularProgressDrawable(imageView.context))
                .error(R.drawable.placeholder)
                .thumbnail(if (thumbnail) 0.4f else 1.0f)
                .listener(SvgSoftwareLayerSetter())

            requestBuilder.load(it).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("android:selected")
    fun selected(view: View, selected: Boolean) {
        view.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("visibleOrGone")
    fun visibleOrGone(view: View, visible: Boolean) {
        view.visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @JvmStatic
    fun circularProgressDrawable(context: Context): CircularProgressDrawable {
        val progress = CircularProgressDrawable(context)
        progress.setColorSchemeColors(com.github.tak8997.coinranking.R.color.colorAccent)
        progress.strokeWidth = 5f
        progress.centerRadius = 30f
        progress.start()
        return progress
    }
}