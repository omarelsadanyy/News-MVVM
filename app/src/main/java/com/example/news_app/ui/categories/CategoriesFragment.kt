package com.example.news_app.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.example.news_app.R
import com.example.news_app.databinding.FragmentcategoriesBinding

class CategoriesFragment:Fragment() {


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
        viewBinding=DataBindingUtil.inflate(inflater, R.layout.fragmentcategories,container,false)
       return  viewBinding.root
    }
   val category= listOf(
       Category("sports", R.drawable.sports, R.string.sports, R.color.Red),
       Category("technology", R.drawable.politics, R.string.Politics, R.color.blue),
       Category("health", R.drawable.health, R.string.Health, R.color.pink),
       Category("business", R.drawable.bussines, R.string.Business, R.color.dark_orange),
       Category("general", R.drawable.environment, R.string.Enviroment, R.color.light_blue),
       Category("science", R.drawable.science, R.string.Science, R.color.yellow)
   )

    lateinit var  category_title: TextView
    lateinit var viewBinding: FragmentcategoriesBinding
    val adapter= CategoriesAdapter( category)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initview()
    }

    private fun initview() {
        category_title=requireActivity().findViewById(R.id.category_title_name)
       viewBinding.recyclerCategories.adapter=adapter
        adapter.onItemClickListener=object : CategoriesAdapter.OnItemClickListener {
            override fun onitemclick(position: Int, category: Category) {
            oncategoryclickListener?.oncategoryclick(category)
            }
        }
    }

    var oncategoryclickListener : OnCategoryClickListener?=null
    interface OnCategoryClickListener{
        fun oncategoryclick(category: Category)
    }
}