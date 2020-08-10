package com.aydar.featurebreedphoto.presentation

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.aydar.featurebreedphoto.BreedPhotoCommands
import com.aydar.featurebreedphoto.R
import kotlinx.android.synthetic.main.fragment_breed_photo.*
import org.koin.android.viewmodel.ext.android.viewModel

class BreedPhotoFragment : Fragment() {

    private val viewModel: BreedPhotoViewModel by viewModel()
    private var carouselAdapter: CarouselAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breed_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: BreedPhotoFragmentArgs by navArgs()
        viewModel.args = args
        carouselAdapter = CarouselAdapter(carouselView, layoutInflater) {
            viewModel.handlePhotoLike(it)
        }
        setupViewModelObservers()
        viewModel.showDogPhotos(args.breedName)
        setupToolbar(args.breedName)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mnu_breed_photos, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val image = carouselAdapter?.currentImage
                image?.let { viewModel.onShareActionClicked(it) }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewModelObservers() {
        viewModel.photosLiveData.observe(viewLifecycleOwner, Observer {
            carouselAdapter?.submitPhotos(it)
        })

        viewModel.event.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BreedPhotoCommands.ShareImage -> {
                    shareImage(it.uri)
                }
            }
        })
    }

    private fun shareImage(uri: Uri) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/*"
        share.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(share, "Share via"))
    }

    private fun setupToolbar(breed: String) {
        val toolbar = inc_toolbar as Toolbar
        toolbar.setTitleTextColor(Color.BLACK)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = breed
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        carouselAdapter?.clearResources()
        carouselAdapter = null
        super.onDestroy()
    }
}