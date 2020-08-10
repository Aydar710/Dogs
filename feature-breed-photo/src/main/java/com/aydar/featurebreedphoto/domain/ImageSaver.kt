package com.aydar.featurebreedphoto.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageSaver(private val context: Context) {

    suspend fun saveImage(image: ImageView): Uri? {
        return withContext(Dispatchers.IO) {
            val drawable: BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap = drawable.bitmap
            val imagesFolder = File(context.cacheDir, "images")
            var uri: Uri? = null
            try {
                imagesFolder.mkdirs()
                val file = File(imagesFolder, "shared_image.jpg")
                val stream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
                stream.flush()
                stream.close()
                uri = FileProvider.getUriForFile(
                    context,
                    "com.aydar.dogs.FileProvider",
                    file
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            uri
        }
    }
}