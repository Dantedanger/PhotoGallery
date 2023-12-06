package com.sample.photogallery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
private const val TAG = "PhotoGalleryActivity"
abstract class PhotoGalleryActivity : AppCompatActivity(), PhotoGalleryFragment.Callbacks {

        private val photoGalleryViewModel:
                PhotoGalleryViewModel by lazy {
            ViewModelProviders.of(this).get(PhotoGalleryViewModel::class.java)
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_gallery)
        val isFragmentContainerEmpty =
            savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, PhotoGalleryFragment.newInstance())
                .commit()
        }
    }
    override fun onDatabaseSelected()
    {
        val fragment = PhotoGalleryDatabaseFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
        photoGalleryViewModel.showDatabaseGallery()
    }
    override fun onAddSelected(item: Item)
    {
        val fragment = PhotoGalleryDatabaseFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
        photoGalleryViewModel.addPhoto(item)
    }
    override fun onDeleteSelected()
    {
        val fragment = PhotoGalleryDatabaseFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
        photoGalleryViewModel.deletephotos()
    }

    companion object {
        fun newIntent(context: Context): Intent
        {
            return Intent(context, PhotoGalleryActivity::class.java)
        }
    }

}