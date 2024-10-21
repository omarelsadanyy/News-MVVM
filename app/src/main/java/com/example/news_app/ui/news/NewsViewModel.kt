package com.example.news_app.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ArticlesItemDTO
import com.example.domain.model.SourcesItemDTO
import com.example.domain.repos.NewsRepository
import com.example.domain.repos.SourcesRepository

import com.example.news_app.ui.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsRepository: NewsRepository,
    val sourcesRepository: SourcesRepository
):ViewModel(){
    val sourcesLiveData= MutableLiveData<List<SourcesItemDTO?>?>()
    val articlesLiveData= MutableLiveData<List<ArticlesItemDTO?>?>()
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

    fun loadNews(source: SourcesItemDTO?, query: String?){

        progressBarVisible.value=true
        viewModelScope.launch {
            val result= newsRepository.getNews(source?.id?:"")
            progressBarVisible.value=false
            articlesLiveData.value=result
            messageLiveData.value= "Doneeeeee"
        }

    }




}