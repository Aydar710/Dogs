package com.aydar.featurefavourites.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aydar.common.FavouriteItem
import com.aydar.featurefavourites.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_breed.view.*

class FavouritesAdapter(private val onItemClicked: (FavouriteItem) -> Unit) :
    ListAdapter<FavouriteItem, FavouritesAdapter.BreedViewHolder>(FavouriteItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BreedViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: FavouriteItem) {
            with(containerView) {
                val photosSize = item.likedPhotos?.size
                photosSize?.let {
                    if (it == 1) {
                        tv_breed.text = "${item.breed} ($it photo)"
                    }
                    if (it > 1) {
                        tv_breed.text = "${item.breed} (${item.likedPhotos?.size} photos)"
                    }
                }

                setOnClickListener {
                    onItemClicked.invoke(item)
                }
            }
        }
    }
}