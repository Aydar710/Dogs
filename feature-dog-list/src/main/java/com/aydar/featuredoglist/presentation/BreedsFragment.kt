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
import com.aydar.featuredoglist.R
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
    }

    private fun setupRecycler() {
        adapter = BreedsAdapter {
            //viewModel.onBreedClicked(it)
            val action = BreedsFragmentDirections.actionBreedsFragmentToBreedPhotoFragment(it.breed)
            findNavController().navigate(action)
        }
        rv_breeds.adapter = adapter
    }
}