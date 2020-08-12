package com.aydar.featurefavouriephotos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.aydar.featurefavouriephotos.R
import kotlinx.android.synthetic.main.fragment_favourites_photos.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavouritePhotosFragment : Fragment() {

    private val viewModel: FavouritePhotosViewModel by viewModel()
    private val args: FavouritePhotosFragmentArgs by navArgs()
    private lateinit var adapter: FavouritePhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.args = args
        setupAdapter()

        setupViewModelObservers()
        viewModel.showPhotos()
    }

    private fun setupViewModelObservers() {
        viewModel.photos.observe(viewLifecycleOwner, Observer {
            adapter.submitPhotos(it)
        })
    }

    private fun setupAdapter() {
        adapter = FavouritePhotosAdapter(carouselViewFavourites, layoutInflater) {
            viewModel.handleLike(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.clearResources()
    }
}