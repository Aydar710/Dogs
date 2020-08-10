package com.aydar.featuresubbreeds.presentation

import androidx.recyclerview.widget.DiffUtil

class SubBreedDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}