package totersapp.marvel.task.utils

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import totersapp.marvel.task.R
import totersapp.marvel.task.domain.model.Thumbnail

@BindingAdapter("image_url", "extension", "image_size")
fun ImageView.loadImage(url: String, extension: String, imageSize: String) {
    Glide.with(context)
        .load("$url/$imageSize.$extension")
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(this)
}

@BindingAdapter("thumbnail", "image_size")
fun ImageView.loadImage(thumbnail: Thumbnail?, imageSize: String) {
    if (thumbnail != null) {
        loadImage(thumbnail.url, thumbnail.extension, imageSize)
    } else {
        loadImage("", "", imageSize)
    }

}

@BindingAdapter("start_date", "end_date")
fun TextView.setRangeDate(startDate: String?, endDate: String?) {
    if (startDate.isNullOrEmpty() || endDate.isNullOrEmpty()) {
        visibility = View.GONE
        return
    }

    visibility = View.VISIBLE
    val start = startDate.split(" ")[0]
    val end = endDate.split(" ")[0]

    val rslt = "${start.replace("-", "/")} - ${end.replace("-", "/")}"
    text = rslt
}

@BindingAdapter("loading_status")
fun LottieAnimationView.setLoadingStatus(isLoading: Boolean) {
    if (isLoading) {
        visibility = View.VISIBLE
        setAnimation("progress_bar.json")
        playAnimation()
        repeatCount = LottieDrawable.INFINITE
    } else {
        visibility = View.GONE
        cancelAnimation()
    }
}

@BindingAdapter("loading_status")
fun FrameLayout.setLoadingStatus(isLoading: Boolean) {
    visibility = if (isLoading) View.VISIBLE
    else View.GONE
}

@BindingAdapter("error_status", "error_message")
fun LottieAnimationView.setErrorStatus(isError: Boolean, errorMessage: String) {
    if (isError) {
        setAnimation(if (errorMessage == context.getString(R.string.no_internet_connection)) "no_internet_connection.json" else "error_dialog_animation.json")
        playAnimation()
        repeatCount = LottieDrawable.INFINITE
    } else {
        cancelAnimation()
    }
}

@BindingAdapter("error_status")
fun ConstraintLayout.setErrorStatus(isError: Boolean) {
    visibility = if (isError) View.VISIBLE
    else View.GONE
}

