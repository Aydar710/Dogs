package com.aydar.featurebreedphoto

import android.net.Uri

sealed class BreedPhotoEvents {
    class ShareImage(val uri: Uri) : BreedPhotoEvents()
    object ShowProgress : BreedPhotoEvents()
    object HideProgress : BreedPhotoEvents()
}