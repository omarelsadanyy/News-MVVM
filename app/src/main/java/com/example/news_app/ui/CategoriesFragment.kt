package com.example.news_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.R

class CategoriesFragment:Fragment() {

    lateinit var  category_title: TextView
    override fun onResume() {
        super.onResume()
        if (isVisible) {
            category_title.text="News App"
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmentcategories,container,false)
    }
   val category= listOf(
       Category("sports", R.drawable.sports, R.string.sports, R.color.Red),
       Category("technology", R.drawable.politics, R.string.Politics, R.color.blue),
       Category("health", R.drawable.health, R.string.Health, R.color.pink),
       Category("business", R.drawable.bussines, R.string.Business, R.color.dark_orange),
       Category("general", R.drawable.environment, R.string.Enviroment, R.color.light_blue),
       Category("science", R.drawable.science, R.string.Science, R.color.yellow)
   )
    lateinit var recyclerView: RecyclerView
    val adapter=CategoriesAdapter( category)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initview()
    }

    private fun initview() {
        recyclerView =requireView().findViewById(R.id.recycler_categories)
        category_title=requireActivity().findViewById(R.id.category_title_name)
        recyclerView.adapter=adapter
        adapter.onItemClickListener=object :CategoriesAdapter.OnItemClickListener{
            override fun onitemclick(position: Int, category: Category) {
            oncategoryclickListener?.oncategoryclick(category)
            }
        }
    }

    var oncategoryclickListener :OnCategoryClickListener?=null
    interface OnCategoryClickListener{
        fun oncategoryclick(category: Category)
    }
}