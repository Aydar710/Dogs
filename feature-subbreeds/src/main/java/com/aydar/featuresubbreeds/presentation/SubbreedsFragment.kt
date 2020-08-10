package com.aydar.featuresubbreeds.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aydar.featuresubbreeds.R
import com.aydar.featuresubbreeds.SubbreedsEvents
import kotlinx.android.synthetic.main.fragment_subbreeds.*
import org.koin.android.viewmodel.ext.android.viewModel

class SubbreedsFragment : Fragment() {

    private val viewModel: SubbreedsViewModel by viewModel()
    private lateinit var adapter: SubbreedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subbreeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: SubbreedsFragmentArgs by navArgs()
        setupToolbar(args.dog.breed)
        setupRecycler()
        setupViewModelObservers()
        viewModel.args = args

        viewModel.showSubbreeds()
    }

    private fun setupToolbar(breed: String) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        (toolbar as Toolbar).setupWithNavController(navController, appBarConfiguration)
        (toolbar as Toolbar).title = breed
    }

    private fun setupViewModelObservers() {
        viewModel.subbreeds.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.events.observe(viewLifecycleOwner, Observer {
            when (it) {
                is SubbreedsEvents.NavigateToBreedPhotos -> {
                    navigateToBreedPhoto(it.subbreed)
                }
            }
        })
    }

    private fun navigateToBreedPhoto(subbreed: String) {
        val args: SubbreedsFragmentArgs by navArgs()
        val action =
            SubbreedsFragmentDirections.actionSubbreedsFragmentToBreedPhotoFragment(
                args.dog.breed,
                subbreed,
                true
            )
        findNavController().navigate(action)
    }

    private fun setupRecycler() {
        adapter = SubbreedsAdapter {
            viewModel.onSubbreedClicked(it)
        }
        rv_subbreeds.adapter = adapter
    }
}