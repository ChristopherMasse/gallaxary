package com.christophermasse.spaceshowcase.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.christophermasse.spaceshowcase.data.ImageWrapper
import com.christophermasse.spaceshowcase.data.api.AuthInterceptor
import com.christophermasse.spaceshowcase.data.repository.ImageRepo
import com.christophermasse.spaceshowcase.data.repository.ImageRepository
import kotlinx.coroutines.*
import timber.log.Timber


class GalleryViewModel: ViewModel() {

    private var imageList: MutableLiveData<List<ImageWrapper>> = MutableLiveData();

    private var searchedImage:  MutableLiveData<ImageWrapper> = MutableLiveData();

    private var clickedItem: MutableLiveData<ImageWrapper> = MutableLiveData()

    private var isLoadingGallery: MutableLiveData<Boolean> = MutableLiveData(true)

    private var repo: ImageRepo

    var job: Job? = null

    var loadMultipleJob: Job? = null

    init{
        Timber.d("Created an instance of ${GalleryViewModel::class.java.simpleName}")
        repo = ImageRepository(AuthInterceptor())
    }

    fun searchForImageAtDate(dateString:String)
    {

        Timber.d("searchForImageAtDate($dateString)")
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getImageAtDate(dateString)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Timber.d("success")
                    searchedImage.postValue(response.body())
                } else {
                    //TODO: handle the error
                }

            }
        }
    }
    fun findImagesInRange(start:String, end:String){
        loadMultipleJob = CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getImagesBetweenDates(start, end)
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    isLoadingGallery.postValue(false)
                    imageList.postValue(response.body())
                }
            }
        }
    }

    fun chooseItem(pos: Int) {
        clickedItem.postValue(imageList.value?.get(pos))
    }

    fun getImageListLiveData(): LiveData<List<ImageWrapper>> {
        return imageList
    }

    fun getClickedItemLiveData(): LiveData<ImageWrapper> {
        return clickedItem
    }

    fun getSearchedImageLiveData(): LiveData<ImageWrapper> {
        return searchedImage
    }

    fun isLoading(): LiveData<Boolean>{
        return isLoadingGallery
    }
}