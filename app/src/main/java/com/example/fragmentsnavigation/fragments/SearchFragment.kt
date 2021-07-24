package com.example.fragmentsnavigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fragmentsnavigation.databinding.FragmentSearchBinding
import com.example.fragmentsnavigation.viewModel.RepViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: RepViewModel by activityViewModels()
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
        viewModel.repoName.observe(viewLifecycleOwner, Observer {
            binding.nameRepo.text = it
        })
        binding.searchButton.setOnClickListener {
            searchRepos()
            viewModel.repoName.observe(viewLifecycleOwner, Observer {
                binding.nameRepo.text = it
            })
            viewModel.userName.observe(viewLifecycleOwner, Observer {
                binding.authorRepo.text = it
            })
            viewModel.lastCommitDate.observe(viewLifecycleOwner, Observer {
                binding.dateLastCommit.text = it
            })
        }

    }

    fun searchRepos(){
        viewModel.setSearchName(binding.searchText.text.toString())
        viewModel.getGitRepo()
    }

    //навигация ко второму фрагменту
    //нужно организовать переход с изм данных
    fun moveOn() {
        val action = SearchFragmentDirections.actionSearchFragmentToEditFragment()
        findNavController().navigate(action)
    }
}