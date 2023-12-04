package com.sample.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.sample.photogallery.api.FlickrFetchr

class PhotoGalleryViewModel : ViewModel() {
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()

    init {
        mutableSearchTerm.value = "planets"
        galleryItemLiveData = mutableSearchTerm.switchMap { searchTerm ->
                flickrFetchr.searchPhotos(searchTerm)
            }
    }
    fun fetchPhotos(query: String = "") {
        mutableSearchTerm.value = query
    }

}
