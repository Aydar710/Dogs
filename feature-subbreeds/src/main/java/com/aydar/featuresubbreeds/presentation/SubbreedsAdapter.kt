package com.aydar.featuresubbreeds.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aydar.featuresubbreeds.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_breed.view.*

class SubbreedsAdapter(private val onBreedClicked: (String) -> Unit) :
    ListAdapter<String, SubbreedsAdapter.SubBreedViewHolder>(SubBreedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubBreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_breed, parent, false)
        return SubBreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubBreedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SubBreedViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(breed: String) {
            with(containerView) {
                tv_breed.text = breed

                setOnClickListener {
                    onBreedClicked.invoke(breed)
                }
            }
        }
    }
}