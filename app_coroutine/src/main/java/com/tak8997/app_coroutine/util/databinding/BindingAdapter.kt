package com.tak8997.app_coroutine.util.databinding

import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.tak8997.app_coroutine.lib.SvgSoftwareLayerSetter
import com.tak8997.app_coroutine.R
import com.tak8997.app_coroutine.lib.GlideApp


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCompat", "thumbnail", requireAll = false)
    fun setSrcCompat(imageView: ImageView, imageUrl: String?, thumbnail: Boolean = false) {
        imageUrl?.let {
            val requestBuilder = GlideApp.with(imageView)
                .`as`(PictureDrawable::class.java)
//                .placeholder(circularProgressDrawable(imageView.context))
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

//    @JvmStatic
//    fun circularProgressDrawable(context: Context): CircularProgressDrawable {
//        val progress = CircularProgressDrawable(context)
//        progress.setColorSchemeColors(R.color.colorAccent)
//        progress.strokeWidth = 5f
//        progress.centerRadius = 30f
//        progress.start()
//        return progress
//    }
}