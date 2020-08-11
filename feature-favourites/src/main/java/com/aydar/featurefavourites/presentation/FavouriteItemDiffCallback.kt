package com.aydar.featurefavourites.presentation

import androidx.recyclerview.widget.DiffUtil
import com.aydar.featurefavourites.FavouriteItem

class FavouriteItemDiffCallback : DiffUtil.ItemCallback<FavouriteItem>() {

    override fun areItemsTheSame(oldItem: FavouriteItem, newItem: FavouriteItem): Boolean =
        oldItem.breed == newItem.breed

    override fun areContentsTheSame(oldItem: FavouriteItem, newItem: FavouriteItem): Boolean =
        oldItem == newItem
}