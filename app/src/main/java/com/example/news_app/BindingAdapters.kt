package com.example.news_app

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

@BindingAdapter("app:ImageUrl")
fun loadImgFromUrl(image:ImageView,url:String?){
    Glide.with(image)
        .load(url)
        .into(image)
}
@BindingAdapter("app:imageResource")
fun setImageResource(image: ImageView,Id:Int){
   image.setImageResource(Id)
}
@BindingAdapter("app:CardBackColor")
fun changeCardBackground(cardView: MaterialCardView,id:Int ){
    cardView.setCardBackgroundColor(cardView.context?.getColor(id)?:0)
//    holder.parent.setCardBackgroundColor(holder.parent.context?.getColor(cate.backgroundcolor)?:0)
}