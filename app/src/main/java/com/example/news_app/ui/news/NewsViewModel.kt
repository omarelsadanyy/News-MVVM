package com.example.news_app.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.Constants
import com.example.news_app.database.MyDataBase
import com.example.news_app.model.ArticlesItem
import com.example.news_app.model.SourcesItem
import com.example.news_app.repos.news.NewOnlineDataSourceImpL
import com.example.news_app.repos.news.NewsOnlineDataSource
import com.example.news_app.repos.news.NewsRepository
import com.example.news_app.repos.news.NewsRepositoryImpL
import com.example.news_app.repos.sources.SourcesOfflineDataSource
import com.example.news_app.repos.sources.SourcesOfflineDataSourceImpL
import com.example.news_app.repos.sources.SourcesOnlineDataSource
import com.example.news_app.repos.sources.SourcesOnlineDataSourceImpL
import com.example.news_app.repos.sources.SourcesRepository
import com.example.news_app.repos.sources.SourcesRepositoryImpL
import com.example.news_app.ui.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor( val newsRepository: NewsRepository,
                                         val sourcesRepository: SourcesRepository):ViewModel(){
    val sourcesLiveData= MutableLiveData<List<SourcesItem?>?>()
    val articlesLiveData= MutableLiveData<List<ArticlesItem?>?>()
    val progressBarVisible=MutableLiveData(false)
    val messageLiveData=MutableLiveData<String>()


    fun getNewsSources(category:Category) {
        progressBarVisible.value=true
        viewModelScope.launch {
            val result= sourcesRepository.getSources(category.categoryid)
            progressBarVisible.value=false
                 sourcesLiveData.value=result
        }


    }

    fun loadNews(source: SourcesItem?, query: String?){

        progressBarVisible.value=true
        viewModelScope.launch {
            val result= newsRepository.getNews(source?.id?:"")
            progressBarVisible.value=false
            articlesLiveData.value=result
            messageLiveData.value= "Doneeeeee"
        }

    }




}