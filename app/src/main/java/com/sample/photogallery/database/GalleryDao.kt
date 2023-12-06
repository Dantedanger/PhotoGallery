package com.sample.photogallery.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.photogallery.Item
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao

interface GalleryDao {
    @Query("SELECT * FROM gallery")
    fun getphotos(): LiveData<List<Item>>
    @Insert
    fun addphoto(item: Item)

    @Query("DELETE FROM gallery")
    fun deletephotos()

    @Query("SELECT * FROM gallery WHERE url=(:url)")
    fun getphoto(url: String): Item?
}