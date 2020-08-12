package com.aydar.featurefavouriephotos.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.aydar.featurefavouriephotos.R
import com.aydar.model.LikedPhoto
import com.bumptech.glide.Glide
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ViewListener
import kotlinx.android.synthetic.main.item_breed_photo.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class FavouritePhotosAdapter(
    private var carouselView: CarouselView?,
    private var inflater: LayoutInflater?,
    private val onLikeClicked: (LikedPhoto) -> Unit
) {

    private val images = mutableListOf<ImageView>()

    fun submitPhotos(items: List<LikedPhoto>) {
        val viewListener = setupViewListener(items)

        carouselView?.setViewListener(viewListener)
        carouselView?.pageCount = items.size
    }

    private fun setupViewListener(photos: List<LikedPhoto>): ViewListener {
        return ViewListener { position ->
            val photoView = inflater!!.inflate(R.layout.item_breed_photo, null)

            with(photoView) {
                loadImage(photoView, photos, position)

                if (photos[position].isLiked) {
                    iv_like.setImageResource(R.drawable.ic_like_filled)
                } else {
                    iv_like.setImageResource(R.drawable.ic_like)
                }

                iv_like.setOnClickListener {
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

    private fun loadImage(
        photoView: View,
        photos: List<LikedPhoto>,
        position: Int
    ) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val bitmap = Glide.with(photoView)
                    .asBitmap()
                    .load(photos[position].url)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.error_placeholder)
                    .submit()
                    .get()


                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val compressedBitmap =
                    BitmapFactory.decodeByteArray(
                        stream.toByteArray(),
                        0,
                        stream.toByteArray().size
                    )

                withContext(Dispatchers.Main) {
                    photoView.iv_breed_photo.setImageBitmap(compressedBitmap)
                }
            }
        }
    }

    fun clearResources() {
        carouselView = null
        inflater = null
        images.clear()
    }
}