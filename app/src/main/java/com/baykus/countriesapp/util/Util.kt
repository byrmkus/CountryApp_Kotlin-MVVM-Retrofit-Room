package com.baykus.countriesapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.baykus.countriesapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.bumptech.glide.request.RequestOptions


/*
fun String.myExtension(myParameter:String){

    println(myParameter)
}
*/

fun ImageView.downloadFromUrlImage(url: String?, progressDrawable: CircularProgressDrawable) {
    val option = RequestOptions()
        .placeholder(progressDrawable)  // resim yükleme esnasında progressbar çıkıyor
        .error(R.drawable.carpi)           //hata esnasında gösterilecek resim


    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}

//progres bar fonksiyonu
fun placeHolderProgresBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:dowloadUrl")
fun dowloadImage(view: ImageView,url: String?){

    view.downloadFromUrlImage(url, placeHolderProgresBar(view.context))
}