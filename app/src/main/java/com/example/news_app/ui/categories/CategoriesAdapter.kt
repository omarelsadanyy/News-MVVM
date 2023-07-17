package com.example.news_app.ui.categories

import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.news_app.R
import com.example.news_app.databinding.ItemCategoryLeftSidedBinding
import com.example.news_app.databinding.ItemCategoryRightSidedBinding


import com.google.android.material.card.MaterialCardView

class CategoriesAdapter(val categorylist:List<Category>):RecyclerView.Adapter<ViewHolder>(){

   class ViewholderLeft(val itemCateLeft: ItemCategoryLeftSidedBinding):ViewHolder(itemCateLeft.root){
       fun bindLeft(item:Category?){
           itemCateLeft.item=item
           itemCateLeft.invalidateAll()
       }
   }
    class ViewholderRight(val itemCateRight: ItemCategoryRightSidedBinding):ViewHolder(itemCateRight.root){

     fun bindRight(item:Category?){
           itemCateRight.item=item
         itemCateRight.invalidateAll()
        }
    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

       if( viewType==isleftsided ){
           val viewleftBinding: ItemCategoryLeftSidedBinding =DataBindingUtil.inflate(
               LayoutInflater.from(parent.context), R.layout.item_category_left_sided,parent,false
           )
           return ViewholderLeft(viewleftBinding)
       }else{
           val viewrightBinding: ItemCategoryRightSidedBinding =DataBindingUtil.inflate(
               LayoutInflater.from(parent.context), R.layout.item_category_right_sided,parent,false
           )
           return ViewholderRight(viewrightBinding)

       }
   }
   val isrightsided=10
   val isleftsided=20
    override fun getItemViewType(position: Int): Int {
        if(position%2==0){
            return isleftsided
        }
        return isrightsided
    }

   override fun onBindViewHolder(holder:ViewHolder, position: Int) {
     val cate= categorylist[position]
     if(getItemViewType(position)==isleftsided){
         (holder as ViewholderLeft).bindLeft(cate)
     }else{
         (holder as ViewholderRight).bindRight(cate)
     }

       onItemClickListener.let {
           holder.itemView.setOnClickListener {
               onItemClickListener?.onitemclick(position,cate)
           }
       }
   }

         // call back for the category
    var onItemClickListener: OnItemClickListener?=null
   interface OnItemClickListener{
       fun onitemclick(position: Int,category: Category)
   }

   override fun getItemCount(): Int =categorylist.size
}