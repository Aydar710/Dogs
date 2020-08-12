package com.aydar.featuredoglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aydar.common.showLoadingErrorDialog
import com.aydar.featuredoglist.BreedsEvents
import com.aydar.featuredoglist.R
import com.aydar.model.Dog
import kotlinx.android.synthetic.main.fragment_breeds.*
import org.koin.android.viewmodel.ext.android.viewModel

class BreedsFragment : Fragment() {

    private val viewModel: BreedsViewModel by viewModel()
    private lateinit var adapter: BreedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecycler()
        setupViewModelObservers()
        viewModel.showDogs()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        (toolbar as Toolbar).setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupViewModelObservers() {
        viewModel.dogsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.events.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BreedsEvents.NavigateToBreedPhotos -> {
                    navigateToBreedPhoto(it.dog)
                }
                is BreedsEvents.NavigateToSubbreeds -> {
                    navigateToSubbreeds(it.dog)
                }
                is BreedsEvents.ShowProgress -> showProgress()
                is BreedsEvents.HideProgress -> hideProgress()
                is BreedsEvents.ShowError -> showError()
            }
        })
    }

    private fun navigateToBreedPhoto(dog: Dog) {
        val action = BreedsFragmentDirections.actionBreedsFragmentToBreedPhotoFragment(dog.breed)
        findNavController().navigate(action)
    }

    private fun navigateToSubbreeds(dog: Dog) {
        val action = BreedsFragmentDirections.actionBreedsFragmentToSubbreedsFragment(dog)
        findNavController().navigate(action)
    }

    private fun setupRecycler() {
        adapter = BreedsAdapter {
            viewModel.onBreedClicked(it)
        }
        rv_breeds.adapter = adapter
    }

    private fun showProgress() {
        pb_breeds.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pb_breeds.visibility = View.GONE
    }

    private fun showError() {
        showLoadingErrorDialog()
    }
}