package com.anangkur.kotlinexpertsubmission.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.anangkur.kotlinexpertsubmission.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun showToastShort(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showToastLong(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun ImageView.setImageUrl(url: String){
    Glide.with(this)
        .load(url)
        .apply(RequestOptions().error(R.color.gray))
        .into(this)
}

fun hideSoftKeyboard(activity: Activity) {
    val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        activity.currentFocus!!.windowToken, 0
    )
}