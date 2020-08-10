package com.aydar.featurebreedphoto.presentation

import android.view.LayoutInflater
import com.aydar.featurebreedphoto.R
import com.aydar.model.Photo
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ViewListener
import kotlinx.android.synthetic.main.item_breed_photo.view.*

//TODO: Fix memory leak
class CarouselAdapter(
    private val carouselView: CarouselView,
    private val inflater: LayoutInflater,
    private val onLikeClicked: (Photo) -> Unit
) {

    fun submitPhotos(photos: List<Photo>) {
        val viewListener = setupViewListener(photos)

        carouselView.setViewListener(viewListener)
        carouselView.pageCount = photos.size
    }

    private fun setupViewListener(photos: List<Photo>): ViewListener {
        return ViewListener { position ->
            val photoView = inflater.inflate(R.layout.item_breed_photo, null)

            with(photoView) {
                Picasso.get()
                    .load(photos[position].url)
                    .into(iv_breed_photo)

                if (photos[position].isLiked) {
                    iv_like.setImageResource(R.drawable.ic_like_filled)
                } else {
                    iv_like.setImageResource(R.drawable.ic_like)
                }

                photoView.iv_like.setOnClickListener {
                    photos[position].isLiked = !photos[position].isLiked
                    onLikeClicked.invoke(photos[position])
                    if (photos[position].isLiked) {
                        iv_like.setImageResource(R.drawable.ic_like_filled)
                    } else {
                        iv_like.setImageResource(R.drawable.ic_like)
                    }
                }
            }
            photoView
        }
    }
}