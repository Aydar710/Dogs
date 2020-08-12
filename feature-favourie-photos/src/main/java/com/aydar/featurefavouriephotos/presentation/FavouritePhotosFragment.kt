package com.aydar.featurefavouriephotos.presentation

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
import com.aydar.featurefavouriephotos.FavouritePhotosEvents
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

        setupToolbar()
        viewModel.args = args
        setupAdapter()

        setupViewModelObservers()
        viewModel.showPhotos()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mnu_breed_photos, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val image = adapter.currentImage
                image?.let { viewModel.onShareActionClicked(it) }
            }
            android.R.id.home -> {
                activity?.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewModelObservers() {
        viewModel.photos.observe(viewLifecycleOwner, Observer {
            adapter.submitPhotos(it)
        })

        viewModel.event.observe(viewLifecycleOwner, Observer {
            when (it) {
                is FavouritePhotosEvents.ShareImage -> {
                    shareImage(it.uri)
                }
            }
        })
    }

    private fun setupAdapter() {
        adapter = FavouritePhotosAdapter(carouselViewFavourites, layoutInflater) {
            viewModel.handleLike(it)
        }
    }

    private fun setupToolbar() {
        val toolbar = inc_toolbar as Toolbar
        toolbar.setTitleTextColor(Color.BLACK)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        setHasOptionsMenu(true)
        toolbar.title = args.favouriteItem.breed
    }

    private fun shareImage(uri: Uri) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/*"
        share.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(share, "Share via"))
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.clearResources()
    }
}