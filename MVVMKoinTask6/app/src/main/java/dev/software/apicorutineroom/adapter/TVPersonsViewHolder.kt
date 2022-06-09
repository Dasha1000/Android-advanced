package dev.software.apicorutineroom.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import dev.software.apicorutineroom.databinding.ItemPersonBinding
import dev.software.apicorutineroom.model.TVPersons

class TVPersonsViewHolder(
    private val binding: ItemPersonBinding,
    private val onTVPersonsClicked: (TVPersons) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tvPerson: TVPersons) {
        with(binding) {

            picture.load(tvPerson.person?.image?.original) {
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }

            textName.text = tvPerson.person?.name

            root.setOnClickListener {
                onTVPersonsClicked(tvPerson)
            }
        }

    }
}