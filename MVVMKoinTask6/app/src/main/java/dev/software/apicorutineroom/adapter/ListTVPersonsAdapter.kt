package dev.software.apicorutineroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.software.apicorutineroom.databinding.ItemLoadingBinding
import dev.software.apicorutineroom.databinding.ItemPersonBinding
import dev.software.apicorutineroom.model.PagingData
import dev.software.apicorutineroom.model.TVPersons

class ListTVPersonsAdapter(
    private val onTVPersonsClicked: (TVPersons) -> Unit
) : ListAdapter<PagingData<TVPersons>, RecyclerView.ViewHolder>(DIFF_UTIL) {

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
                TVPersonsViewHolder(
                    binding = ItemPersonBinding.inflate(layoutInflater, parent, false),
                    onTVPersonsClicked = onTVPersonsClicked
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder(
                    binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> error("Incorrect view type - $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tvViewHolder = holder as? TVPersonsViewHolder ?: return
        val item = (getItem(position) as? PagingData.Content)?.data ?: return
        tvViewHolder.bind(item)
    }

    companion object {

        private const val TYPE_TV = 0
        private const val TYPE_LOADING = 1

        private var DIFF_UTIL = object : DiffUtil.ItemCallback<PagingData<TVPersons>>() {

            override fun areItemsTheSame(
                oldItem: PagingData<TVPersons>,
                newItem: PagingData<TVPersons>
            ): Boolean {
                val old = oldItem as? PagingData.Content
                val new = newItem as? PagingData.Content
                return old?.data?.person?.id == new?.data?.person?.id
                //return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PagingData<TVPersons>,
                newItem: PagingData<TVPersons>
            ): Boolean {
                val old = oldItem as? PagingData.Content
                val new = newItem as? PagingData.Content
                return old?.data == new?.data
                //return oldItem == newItem
            }
        }
    }
}
