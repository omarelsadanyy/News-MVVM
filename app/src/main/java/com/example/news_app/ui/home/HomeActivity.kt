package com.example.news_app.ui.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.news_app.R
import com.example.news_app.databinding.ActivityHomeBinding
import com.example.news_app.databinding.AppBarHomeBinding
import com.example.news_app.ui.categories.CategoriesFragment
import com.example.news_app.ui.categories.Category
import com.example.news_app.ui.news.NewsFragment


class HomeActivity : AppCompatActivity() {


private val categoriesFragment= CategoriesFragment()
    lateinit var drawericon:ImageView
    lateinit var  category_title:TextView
   lateinit var viewBinding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         viewBinding=DataBindingUtil.setContentView(this,R.layout.activity_home)
         pushfragment(categoriesFragment)
        drawericon=findViewById(R.id.drawer_icon)
        category_title =findViewById(R.id.category_title_name)


        //sending the category
        categoriesFragment.oncategoryclickListener= object : CategoriesFragment.OnCategoryClickListener{
            override fun oncategoryclick(category: Category) {
                category_title.text=category.categoryid
                pushfragment(NewsFragment.getinstance(category),true)
            }

        }

        drawericon.setOnClickListener {
            viewBinding.drawerLayout.open()
        }
       viewBinding.linearCategories.setOnClickListener {

            pushfragment(categoriesFragment)
            viewBinding.drawerLayout.close()
        }

    }
    fun pushfragment(fragment: Fragment,addtobackstack:Boolean=false){
          val fragmenttransaction =supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
        if(addtobackstack){
            fragmenttransaction.addToBackStack("")
        }
        fragmenttransaction.commit()

    }
}