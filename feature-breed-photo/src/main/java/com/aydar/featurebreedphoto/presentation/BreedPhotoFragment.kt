package com.aydar.featurebreedphoto.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.aydar.featurebreedphoto.R
import kotlinx.android.synthetic.main.fragment_breed_photo.*
import org.koin.android.viewmodel.ext.android.viewModel

class BreedPhotoFragment : Fragment() {

    private val viewModel: BreedPhotoViewModel by viewModel()
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breed_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carouselAdapter = CarouselAdapter(carouselView)
        val args: BreedPhotoFragmentArgs by navArgs()
        setupViewModelObservers()
        viewModel.showDogPhotos(args.breedName)
        setupToolbar(args.breedName)
    }

    private fun setupViewModelObservers() {
        viewModel.photosLiveData.observe(viewLifecycleOwner, Observer {
            carouselAdapter.submitPhotos(it)
        })
    }

    private fun setupToolbar(breed : String) {
        val toolbar = inc_toolbar as Toolbar
        toolbar.setTitleTextColor(Color.BLACK)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = breed
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}