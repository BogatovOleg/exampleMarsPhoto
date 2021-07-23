package com.example.fragmentsnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fragmentsnavigation.databinding.FragmentEditBinding
import com.example.fragmentsnavigation.viewModel.RepViewModel

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private val viewModel: RepViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancelButton.setOnClickListener {
            moveOn()
        }
        binding.saveButton.setOnClickListener {
            moveOn()
        }
    }

    fun moveOn() {
        val action = EditFragmentDirections.actionEditFragmentToSearchFragment()
        findNavController().navigate(action)
    }
}