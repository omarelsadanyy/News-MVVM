package com.example.news_app.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.Api.ApiManager
import com.example.news_app.Constants
import com.example.news_app.R
import com.example.news_app.model.ArticlesItem
import com.example.news_app.model.NewsResponse
import com.example.news_app.model.SourcesItem
import com.example.news_app.model.SourcesResponse
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.fixedRateTimer

class NewsFragment:Fragment() {
companion object{
var sourcesItemsearch:SourcesItem?=null
var articlesItem:ArticlesItem?=null
   //open newsfragment with the choosen category
    fun getinstance(category: Category):NewsFragment{
        val fragment= NewsFragment()
         fragment.category=category
        return fragment
    }
}
    lateinit var category: Category
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmentnews,container,false)
    }

 lateinit var progressBar: ProgressBar
 lateinit var tabLayout: TabLayout
 lateinit var recyclerView: RecyclerView
 lateinit var search_icon:SearchView
 var newsContentFragment =NewsContentFragment()
 val adapter =NewsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
        getNewsSources()
        showNewsContent()
        searchNews()
        searchClose()
    }

    private fun showNewsContent() {
      adapter.onItemClickListener=object :NewsAdapter.OnItemClickListener{
          override fun onClick(position: Int, items: ArticlesItem) {
              articlesItem=items
               activity?.supportFragmentManager?.beginTransaction()
                   ?.replace(R.id.fragment_container,newsContentFragment)
                   ?.addToBackStack("")
                   ?.commit()
          }

      }
    }

    private fun initviews() {
        progressBar=requireActivity().findViewById(R.id.progress_bar)
        tabLayout=requireActivity().findViewById(R.id.tablayout)
        search_icon=requireActivity().findViewById(R.id.search_ic)
        search_icon.isVisible=true
        recyclerView=requireActivity().findViewById(R.id.recycler_news)
        recyclerView.adapter=adapter
    }
    override fun onStop() {
        super.onStop()
        search_icon.isVisible = false
    }
    private fun getNewsSources() {
        ApiManager.getApis()
            .getNewssources(Constants.apikey, category.categoryid)
            .enqueue(object : Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible=false
                   showtabs(response.body()?.sources)
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    progressBar.isVisible=false
                }

            })
    }

    private fun showtabs(sources: List<SourcesItem?>?) {
         sources?.forEach{ item->
            val tab= tabLayout.newTab()
             // attach the tab with the sourceitem
             tab.setTag(item)
             tab.setText(item?.name)
             tabLayout.addTab(tab)
         }
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
               // val Source= sources?.get(tab?.position?:0)
                val Source = tab?.tag as SourcesItem
                sourcesItemsearch=Source
                loadNews(Source,"")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val Source = tab?.tag as SourcesItem
                sourcesItemsearch=Source
                loadNews(Source,"")
            }

        })
        tabLayout.getTabAt(0)?.select()
    }

        fun loadNews(source:SourcesItem?,query: String?){
            adapter.changeData(null)
              progressBar.isVisible=true
               ApiManager.getApis().getNewsFromSources(Constants.apikey,source?.id?:"",query?:"")
                   .enqueue(object:Callback<NewsResponse>{
                       override fun onResponse(
                           call: Call<NewsResponse>,
                           response: Response<NewsResponse>
                       ) {

                           progressBar.isVisible=false
                           adapter.changeData(response.body()?.articles)
                       }

                       override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                       }

                   })
        }
    fun searchNews(){
        search_icon.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                   loadNews(sourcesItemsearch,query!!.lowercase())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                loadNews(sourcesItemsearch,newText!!.lowercase())
                return true
            }

        })
    }

//    fun loadNewsSearch(source: SourcesItem?,query:String?){
//         ApiManager.getApis().getNewsFromSources(Constants.apikey,source?.id?:"",query?:"")
//             .enqueue(object :Callback<NewsResponse>{
//                 override fun onResponse(
//                     call: Call<NewsResponse>,
//                     response: Response<NewsResponse>
//                 ) {
//                     progressBar.isVisible=false
//                     adapter.changeData(response.body()?.articles)
//                 }
//
//                 override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//
//                 }
//
//             })
//    }
     fun searchClose(){
      search_icon.setOnCloseListener(SearchView.OnCloseListener{

              loadNews(sourcesItemsearch,null)

            return@OnCloseListener false

      })

     }

}


