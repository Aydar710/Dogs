package com.aydar.featurebreedphoto.presentation

import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class CarouselAdapter(private val carouselView: CarouselView) {

    fun submitPhotos(photos: List<String>) {
        val imageListener = ImageListener { position, imageView ->
            Picasso.get()
                .load(photos[position])
                .into(imageView)
        }

        carouselView.setImageListener(imageListener)
        carouselView.pageCount = photos.size
    }
}