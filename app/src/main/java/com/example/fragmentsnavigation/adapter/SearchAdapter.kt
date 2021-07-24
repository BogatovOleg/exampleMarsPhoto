package com.example.fragmentsnavigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentsnavigation.data.RepositoryInfo
import com.example.fragmentsnavigation.databinding.ItemSearchListBinding

class SearchAdapter(private val context: Context, private var dataset: List<RepositoryInfo>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(private val binding: ItemSearchListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val repositoryName = binding.repositoryName
        val userName = binding.username
        val dateLastCommit = binding.lastCommitDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        val binding = ItemSearchListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = dataset
        holder.repositoryName.text = item[position].repositoryName
        holder.userName.text = item[position].repositoryUser
        holder.dateLastCommit.text = item[position].repositoryDate
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun update(newList:List<RepositoryInfo>){
        dataset = newList
        notifyDataSetChanged()
    }

}