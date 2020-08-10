package com.aydar.featurebreedphoto.presentation

import android.view.LayoutInflater
import android.widget.ImageView
import com.aydar.featurebreedphoto.R
import com.aydar.model.Photo
import com.bumptech.glide.Glide
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ViewListener
import kotlinx.android.synthetic.main.item_breed_photo.view.*

class CarouselAdapter(
    private var carouselView: CarouselView?,
    private var inflater: LayoutInflater?,
    private val onLikeClicked: (Photo) -> Unit
) {

    val currentImage: ImageView?
        get() {
            return images[carouselView?.currentItem!!]
        }

    private val images = mutableListOf<ImageView>()

    fun submitPhotos(photos: List<Photo>) {
        val viewListener = setupViewListener(photos)

        carouselView?.setViewListener(viewListener)
        carouselView?.pageCount = photos.size
    }

    private fun setupViewListener(photos: List<Photo>): ViewListener {
        return ViewListener { position ->
            val photoView = inflater!!.inflate(R.layout.item_breed_photo, null)

            with(photoView) {
                Glide.with(photoView)
                    .load(photos[position].url)
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.placeholder)
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

                images.add(iv_breed_photo)
            }
            photoView
        }
    }

    fun clearResources(){
        carouselView = null
        inflater = null
        images.clear()
    }
}