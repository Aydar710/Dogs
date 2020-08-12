package com.aydar.featurefavouriephotos

import android.net.Uri

sealed class FavouritePhotosEvents {
    class ShareImage(val uri: Uri) : FavouritePhotosEvents()
}