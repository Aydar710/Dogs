package com.aydar.data.repository

import com.aydar.core.repository.ILikedPhotosRepository
import com.aydar.model.LikedPhoto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LikedPhotosRepository(private val db: FirebaseFirestore) : ILikedPhotosRepository {

    override suspend fun savePhotoLike(breed: String, photoUrl: String) {
        db.collection(COLLECTION_BREEDS)
            .document(breed)
            .collection(COLLECTION_LIKED_PHOTOS)
            .document(getDocumentUidFromPhotoUrl(photoUrl))
            .set(LikedPhoto(photoUrl))
            .await()
    }

    override suspend fun deletePhotoLike(breed: String, photoUrl: String) {
        db.collection(COLLECTION_BREEDS)
            .document(breed)
            .collection(COLLECTION_LIKED_PHOTOS)
            .document(getDocumentUidFromPhotoUrl(photoUrl))
            .delete()
            .await()
    }

    override suspend fun getLikedPhotos(breed: String): MutableList<LikedPhoto> {
        return db.collection(COLLECTION_BREEDS)
            .document(breed)
            .collection(COLLECTION_LIKED_PHOTOS)
            .get()
            .await()
            .toObjects(LikedPhoto::class.java)
    }

    private fun getDocumentUidFromPhotoUrl(url: String) =
        url
            .split("//")[1]
            .replace("/", "$")


    companion object {
        const val COLLECTION_BREEDS = "breeds"
        const val COLLECTION_LIKED_PHOTOS = "likedPhotos"
    }
}