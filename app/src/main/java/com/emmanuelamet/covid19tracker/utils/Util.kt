package com.emmanuelamet.covid19tracker.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.emmanuelamet.covid19tracker.R
import com.squareup.picasso.Picasso

class Util {
    companion object{
        val BASE_URL = "https://corona.lmao.ninja"
    }


}

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 40f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
    Picasso.get().load(uri).error(R.mipmap.ic_launcher_round).placeholder(progressDrawable).into(this)
}

fun loadImage(view: ImageView, url: String){
    view.loadImage(url, getProgressDrawable(view.context))

}