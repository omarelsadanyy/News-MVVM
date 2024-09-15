package com.example.news_app.ui.news

import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.Api.ApiManager
import com.example.news_app.Constants
import com.example.news_app.R
import com.example.news_app.model.ArticlesItem
import com.example.news_app.model.NewsResponse
import com.example.news_app.model.SourcesItem
import com.example.news_app.model.SourcesResponse
import com.example.news_app.ui.categories.Category
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel :ViewModel(){
    val sourcesLiveData= MutableLiveData<List<SourcesItem?>?>()
    val articlesLiveData= MutableLiveData<List<ArticlesItem?>?>()
    val progressBarVisible=MutableLiveData(false)
    val messageLiveData=MutableLiveData<String>()
    fun getNewsSources(category:Category) {
        progressBarVisible.value=true
        viewModelScope.launch {
            val result= ApiManager.getApis().getNewssources(Constants.apikey, category.categoryid)
            progressBarVisible.value=false
                 sourcesLiveData.value=result.sources
        }

//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                   progressBarVisible.value=false
//                  sourcesLiveData.value=response.body()?.sources
//                //showtabs(response.body()?.sources)
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    progressBarVisible.value=false
//                    messageLiveData.value=t.localizedMessage
//                }
//
//            })
    }

    fun loadNews(source: SourcesItem?, query: String?){

        progressBarVisible.value=true
        viewModelScope.launch {
            val result= ApiManager.getApis().getNewsFromSources(Constants.apikey,source?.id?:"",query?:"")
            progressBarVisible.value=false
            articlesLiveData.value=result.articles
        }
//            .enqueue(object:Callback<NewsResponse>{
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//
//                    progressBarVisible.value=false
//                    articlesLiveData.value=response.body()?.articles
//
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//
//                }
//
//            })
    }




}