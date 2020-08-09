package com.aydar.featuredoglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.aydar.model.Dog

class BreedDiffCallback : DiffUtil.ItemCallback<Dog>() {

    override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean =
        oldItem.breed == newItem.breed

    override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean = oldItem == newItem
}