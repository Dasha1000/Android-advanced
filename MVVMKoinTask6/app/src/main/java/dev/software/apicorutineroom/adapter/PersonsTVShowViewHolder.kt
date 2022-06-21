package dev.software.apicorutineroom.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import dev.software.apicorutineroom.databinding.ItemPersonTvShowBinding
import dev.software.apicorutineroom.model.PersonTVShowInfo


class PersonsTVShowViewHolder(
    private val binding: ItemPersonTvShowBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tvShows: PersonTVShowInfo) {
        with(binding) {

            picture.load(tvShows.image?.original) {
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }
            textName.text = tvShows.name

        }
    }
}