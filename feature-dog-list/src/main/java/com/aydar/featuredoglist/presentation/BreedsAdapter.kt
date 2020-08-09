package com.aydar.featuredoglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aydar.featuredoglist.R
import com.aydar.model.Dog
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_breed.view.*

class BreedsAdapter : ListAdapter<Dog, BreedsAdapter.BreedViewHolder>(BreedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BreedViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(dog: Dog) {
            if (dog.subBreeds != null) {
                dog.subBreeds?.let {
                    if (it.isNotEmpty()) {
                        bindWithSubBreeds(dog)
                    }else{
                        bindWithoutSubBreeds(dog)
                    }
                }
            } else {
                bindWithoutSubBreeds(dog)
            }
        }

        private fun bindWithoutSubBreeds(dog: Dog) {
            with(containerView) {
                tv_breed.text = dog.breed
            }
        }

        private fun bindWithSubBreeds(dog: Dog) {
            with(containerView) {
                dog.subBreeds?.let {
                    tv_breed.text = "${dog.breed} (${it.size} subbreeds)"
                }
            }
        }
    }
}