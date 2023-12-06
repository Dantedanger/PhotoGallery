package com.sample.photogallery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.sample.photogallery.database.GalleryDatabase
import java.util.UUID
import java.util.concurrent.Executors

private const val DATABASE_NAME = "gallery"
class GalleryRepository private constructor(context: Context) {
    private val database: GalleryDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            GalleryDatabase::class.java,
            DATABASE_NAME
        )
        .build()
    fun getPhotos() = database.galleryDao().getphotos()


    fun addPhoto(photo: Item) {
        Thread{
            database.galleryDao().addphoto(photo)
        }.start()
    }

    fun getPhoto(photo: Item) {
        Thread{
            database.galleryDao().getphoto(photo.url)
        }.start()
    }

    fun deleteAllPhotos() {
        Thread{
            database.galleryDao().deletephotos()
        }.start()
    }

    companion object {
        private var INSTANCE: GalleryRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GalleryRepository(context)
            }
        }
        fun get(): GalleryRepository {
            return INSTANCE ?:
            throw
            IllegalStateException("CrimeRepository must be initialized")
        }
    }

}