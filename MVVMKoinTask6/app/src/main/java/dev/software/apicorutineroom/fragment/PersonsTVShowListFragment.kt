package dev.software.apicorutineroom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.software.apicorutineroom.*
import dev.software.apicorutineroom.adapter.ListPersonsTVShowAdapter
import dev.software.apicorutineroom.databinding.FragmentPersonTvshowListBinding
import dev.software.apicorutineroom.model.PagingData

import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class PersonsTVShowListFragment : Fragment() {
    private var _binding: FragmentPersonTvshowListBinding? = null
    private val binding: FragmentPersonTvshowListBinding
        get() = requireNotNull(_binding)

    private val args by navArgs<PersonsTVShowListFragmentArgs>()

    private val viewModel by viewModel<PersonTVShowListViewModel>() {
        parametersOf(args.list)
    }

    private val adapter by lazy() {
        ListPersonsTVShowAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPersonTvshowListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onLoadMore()

        with(binding) {
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            recyclerView.addSpaceDecoration(ITEMS_SPACE)

            recyclerView
                .onPaginationScrollListener(layoutManager, ITEMS_LOAD)
                .onEach {
                    viewModel.onLoadMore()
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            swipeRefresh
                .onRefreshListener()
                .onEach {
                    viewModel.onRefresh()

                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

        }

        viewModel
            .dataFlow
            .onEach { it ->
                binding.swipeRefresh.isRefreshing=false
                adapter.submitList(
                    it.map { PagingData.Content(it) } + PagingData.Loading)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        binding.toolbar.setupWithNavController(findNavController())

    }

    companion object {
        private const val ITEMS_SPACE = 40
        private const val ITEMS_LOAD = 4
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}