package com.aydar.featuredoglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aydar.featuredoglist.R
import org.koin.android.viewmodel.ext.android.viewModel

class BreedsFragment : Fragment() {

    private val viewModel: BreedsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breeds, container, false)

        viewModel.showDogs()
        return view
    }
}