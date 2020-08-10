package com.aydar.featurebreedphoto

import android.net.Uri

sealed class BreedPhotoCommands {
    class ShareImage(val uri: Uri) : BreedPhotoCommands()
}