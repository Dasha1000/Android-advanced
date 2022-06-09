package dev.software.apicorutineroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.software.apicorutineroom.databinding.ItemLoadingBinding
import dev.software.apicorutineroom.databinding.ItemPersonTvShowBinding
import dev.software.apicorutineroom.model.PagingData
import dev.software.apicorutineroom.model.PersonTVShowInfo

class ListPersonsTVShowAdapter() : ListAdapter<PagingData<PersonTVShowInfo>, RecyclerView.ViewHolder>(DIFF_UTIL) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PagingData.Content -> TYPE_TV
            PagingData.Loading -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TV -> {
                PersonsTVShowViewHolder(
                    binding = ItemPersonTvShowBinding.inflate(layoutInflater, parent, false),
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder (
                    binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> error("Incorrect view type - $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tvViewHolder = holder as? PersonsTVShowViewHolder ?: return
        val item = (getItem(position) as? PagingData.Content)?.data ?: return
        tvViewHolder.bind(item)
    }

    companion object {

        private const val TYPE_TV = 0
        private const val TYPE_LOADING = 1

        private var DIFF_UTIL = object : DiffUtil.ItemCallback<PagingData<PersonTVShowInfo>>() {

            override fun areItemsTheSame(
                oldItem: PagingData<PersonTVShowInfo>,
                newItem: PagingData<PersonTVShowInfo>
            ): Boolean {
                val oldCharacter = oldItem as? PagingData.Content
                val newCharacter = newItem as? PagingData.Content
                return oldCharacter?.data?.id == newCharacter?.data?.id
               // return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PagingData<PersonTVShowInfo>,
                newItem: PagingData<PersonTVShowInfo>
            ): Boolean {
                val oldCharacter = oldItem as? PagingData.Content
                val newCharacter = newItem as? PagingData.Content
                return oldCharacter?.data == newCharacter?.data
                //return oldItem == newItem
            }
        }
    }
}
