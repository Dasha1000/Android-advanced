package dev.software.apicorutineroom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import coil.load
import dev.software.apicorutineroom.databinding.FragmentPersonDetailsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsPersonFragment : Fragment() {
    private var _binding: FragmentPersonDetailsBinding? = null
    private val binding: FragmentPersonDetailsBinding
        get() = requireNotNull(_binding)

    private val viewModel by viewModel<DetailsPersonsViewModel> {
        parametersOf(args.id)
    }
    private val args by navArgs<DetailsPersonFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPersonDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.buttonShows.setOnClickListener {
            viewModel.onClick()
        }

        var mutableList = mutableListOf<String>()

        viewModel
            .navigateToTVShow
            .onEach {
                it.forEach {
                    val last = it._links.show.href.split("/").last()
                    mutableList.add(last)
                }

                findNavController().navigate(
                    DetailsPersonFragmentDirections.toPersonTvShow(
                        mutableList.toTypedArray()
                    )
                )
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel
            .personsInfo
            .onEach {
                //TODO errors
                with(binding) {
                    textName.text = "Name: ${it.name}"
                    picture.load(it.image?.original)
                    textBirthday.text = "Birthday: ${it.birthday}"
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.toolbar.setupWithNavController(findNavController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}