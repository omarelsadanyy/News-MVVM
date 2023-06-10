package com.example.news_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.news_app.R
import com.google.android.material.card.MaterialCardView

class CategoriesAdapter(val categorylist:List<Category>):RecyclerView.Adapter<CategoriesAdapter.viewholder>(){

   class viewholder(itemview: View):ViewHolder(itemview){
         val parent:MaterialCardView= itemview.findViewById(R.id.parent_card_view)
           val title:TextView=itemview.findViewById(R.id.title)
           val Image:ImageView=itemview.findViewById(R.id.image)
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
     val view=LayoutInflater.from(parent.context)
        .inflate(
            if( viewType==isleftsided ){
            R.layout.item_category_left_sided
        }else{
            R.layout.item_category_right_sided
        }
            ,parent,false)
      return viewholder(view)
   }
   val isrightsided=10
   val isleftsided=20
    override fun getItemViewType(position: Int): Int {
        if(position%2==0){
            return isleftsided
        }
        return isrightsided
    }

   override fun onBindViewHolder(holder: viewholder, position: Int) {
     val cate= categorylist[position]
      holder.title.setText(cate.titleid)
      holder.Image.setImageResource(cate.imageid)
      //holder.child.setBackgroundResource(cate.backgroundcolor)
       holder.parent.setCardBackgroundColor(holder.parent.context?.getColor(cate.backgroundcolor)?:0)
       onItemClickListener.let {
           holder.itemView.setOnClickListener {
               onItemClickListener?.onitemclick(position,cate)
           }
       }
   }

         // call back for the category
    var onItemClickListener:OnItemClickListener?=null
   interface OnItemClickListener{
       fun onitemclick(position: Int,category: Category)
   }

   override fun getItemCount(): Int =categorylist.size
}