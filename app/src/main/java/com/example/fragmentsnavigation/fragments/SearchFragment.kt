package com.example.fragmentsnavigation.fragments

import android.R.attr.data
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentsnavigation.adapter.SearchAdapter
import com.example.fragmentsnavigation.data.RepositoryInfo
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
        binding.recycleView.layoutManager = LinearLayoutManager(context)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.repoName.observe(viewLifecycleOwner, Observer {
//            binding.nameRepo.text = it
//        })
//        binding.searchButton.setOnClickListener {
//            searchRepos()
//            viewModel.repoName.observe(viewLifecycleOwner, Observer {
//                binding.nameRepo.text = it
//            })
//            viewModel.userName.observe(viewLifecycleOwner, Observer {
//                binding.authorRepo.text = it
//            })
//            viewModel.lastCommitDate.observe(viewLifecycleOwner, Observer {
//                binding.dateLastCommit.text = it
//            })
//        }
        var list = mutableListOf<RepositoryInfo>()

        //var list1: List<RepositoryInfo>
        var adapter = SearchAdapter(requireContext(),list.toList())
        binding.recycleView.adapter = adapter
//        binding.recycleView.adapter = SearchAdapter(requireContext(),list)

        binding.searchButton.setOnClickListener {
//            binding.recycleView.layoutManager = LinearLayoutManager(context)
            searchRepos()
            viewModel.repository.observe(viewLifecycleOwner, Observer {
                list.clear()
                list = it
                adapter.update(list.toList())
                binding.recycleView.invalidate()
                //call update
            })

        }

    }

    fun searchRepos() {
        viewModel.setSearchName(binding.searchText.text.toString())
    }


    fun moveOn() {
        val action = SearchFragmentDirections.actionSearchFragmentToEditFragment()
        findNavController().navigate(action)
    }

}