package com.example.news_app

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.news_app.ui.CategoriesFragment
import com.example.news_app.ui.Category
import com.example.news_app.ui.NewsFragment


class HomeActivity : AppCompatActivity() {


private val categoriesFragment=CategoriesFragment()
    lateinit var drawerLayout: DrawerLayout
    lateinit var drawericon:ImageView
    lateinit var categories:LinearLayout
    lateinit var  category_title:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_home)
         pushfragment(categoriesFragment)
        drawerLayout=findViewById(R.id.drawer_layout)
        drawericon=findViewById(R.id.drawer_icon)
        categories=findViewById(R.id.linear_categories)
        category_title =findViewById(R.id.category_title_name)


        //sending the category
        categoriesFragment.oncategoryclickListener= object :CategoriesFragment.OnCategoryClickListener{
            override fun oncategoryclick(category: Category) {
                category_title.text=category.categoryid
                pushfragment(NewsFragment.getinstance(category),true)
            }

        }

        drawericon.setOnClickListener {
            drawerLayout.open()
        }
        categories.setOnClickListener {

            pushfragment(categoriesFragment)
            drawerLayout.close()
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