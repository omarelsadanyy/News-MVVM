package com.example.news_app.ui.news
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news_app.R
import com.example.news_app.databinding.FragmentnewsBinding
import com.example.news_app.model.ArticlesItem
import com.example.news_app.model.SourcesItem
import com.example.news_app.ui.categories.Category
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment:Fragment() {
companion object{
var sourcesItemSearch:SourcesItem?=null
var articlesItem:ArticlesItem?=null
   //open newsfragment with the choosen category
    fun getinstance(category: Category): NewsFragment {
        val fragment= NewsFragment()
         fragment.category=category
        return fragment
    }
}
    //omaromarr
    lateinit var category: Category
    lateinit var viewDataBinding:FragmentnewsBinding
    lateinit var search_icon:SearchView
    lateinit var  categorytitle: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       // return inflater.inflate(R.layout.fragmentnews,container,false)
       viewDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragmentnews,container,false)
        return viewDataBinding.root
    }


 lateinit var viewModel: NewsViewModel
 var newsContentFragment = NewsContentFragment()
 @Inject lateinit var adapter : NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
        searchNews()
        searchClose()
        viewModel=ViewModelProvider(this).get(NewsViewModel::class.java)
        subscribetoLiveData()
           viewModel.getNewsSources(category)


    }

    private fun subscribetoLiveData() {
    viewModel.progressBarVisible.observe(viewLifecycleOwner,Observer{isVisisble->
       viewDataBinding.progressBar.isVisible=isVisisble
    })
        viewModel.sourcesLiveData.observe(viewLifecycleOwner,Observer{Sources->
           showtabs(Sources)
        })
        viewModel.articlesLiveData.observe(viewLifecycleOwner,Observer{articles->
            adapter.changeData(articles)

        })
        viewModel.messageLiveData.observe(viewLifecycleOwner,Observer{message->
            Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
        })
    }


    private fun initviews() {

        search_icon=requireActivity().findViewById(R.id.search_ic)
        categorytitle =requireActivity().findViewById(R.id.category_title_name)
        search_icon.isVisible=true
        viewDataBinding.recyclerNews.adapter=adapter
        showNewsContent()
    }
    override fun onStop() {
        super.onStop()
      search_icon.isVisible = false
    }


    private fun showtabs(sources: List<SourcesItem?>?) {
         sources?.forEach{ item->
            val tab= viewDataBinding.tablayout.newTab()
             // attach the tab with the sourceitem
             tab.setTag(item)
             tab.setText(item?.name)
             viewDataBinding.tablayout.addTab(tab)
         }
        viewDataBinding.tablayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
               // val Source= sources?.get(tab?.position?:0)
                val Source = tab?.tag as SourcesItem
                sourcesItemSearch =Source
                viewModel.loadNews(Source,"")
                //loadNews(Source,"")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val Source = tab?.tag as SourcesItem
                sourcesItemSearch =Source
                viewModel.loadNews(Source,"")
               // loadNews(Source,"")
            }

        })
        viewDataBinding.tablayout.getTabAt(0)?.select()
    }

    fun showNewsContent() {
        adapter.onItemClickListener=object : NewsAdapter.OnItemClickListener {
            override fun onClick(position: Int, items: ArticlesItem) {
               articlesItem =items
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container,newsContentFragment)
                    ?.addToBackStack("")
                    ?.commit()
            }

        }
    }
    fun searchNews(){
        search_icon.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.loadNews(sourcesItemSearch,query!!.lowercase())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.loadNews(sourcesItemSearch,newText!!.lowercase())
                return true
            }

        })
    }

    fun searchClose(){
          search_icon.setOnSearchClickListener {
              categorytitle.text=null
          }
        search_icon.setOnCloseListener {
            adapter.changeData(null)
            categorytitle.text=category.categoryid
            viewModel.loadNews(sourcesItemSearch, null)
            false
        }


    }
}


