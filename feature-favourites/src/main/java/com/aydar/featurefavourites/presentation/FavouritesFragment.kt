package com.aydar.featurefavourites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aydar.featurefavourites.R
import kotlinx.android.synthetic.main.fragment_favourites.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {

    private val viewModel: FavouritesViewModel by viewModel()
    private lateinit var adapter: FavouritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecycler()
        setupViewModelObservers()
        viewModel.showBreeds()
    }

    private fun setupViewModelObservers() {
        viewModel.favouriteItems.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRecycler(){
        adapter = FavouritesAdapter()
        rv_favourites.adapter = adapter
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar as Toolbar)
        (toolbar as Toolbar).title = getString(R.string.favourites)
    }
}