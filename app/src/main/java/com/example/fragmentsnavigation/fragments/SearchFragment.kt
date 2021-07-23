package com.example.fragmentsnavigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fragmentsnavigation.databinding.FragmentSearchBinding
import com.example.fragmentsnavigation.viewModel.RepViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: RepViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = viewModel.status.value.toString()
        binding.searchButton.setOnClickListener {
            moveOn()
        }

    }

    fun moveOn() {
        val action = SearchFragmentDirections.actionSearchFragmentToEditFragment()
        findNavController().navigate(action)
    }
}