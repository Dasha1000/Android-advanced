package dev.software.apicorutineroom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.software.apicorutineroom.*
import dev.software.apicorutineroom.adapter.ListTVPersonsAdapter
import dev.software.apicorutineroom.model.PagingData
import dev.software.apicorutineroom.databinding.FragmentPersonsListBinding
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVPersonsListFragment : Fragment() {
    private var _binding: FragmentPersonsListBinding? = null
    private val binding: FragmentPersonsListBinding
        get() = requireNotNull(_binding)

    private val viewModel by viewModel<TVPersonsListViewModel>()

    private val adapter by lazy() {
        ListTVPersonsAdapter(
            onTVPersonsClicked = {
                findNavController()
                    .navigate(TVPersonsListFragmentDirections.toDetailsPerson(it.person.id))
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPersonsListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            recyclerView.addSpaceDecoration(ITEMS_SPACE)


            swipeRefresh
                .onRefreshListener()
                .onEach {
                    swipeRefresh.isRefreshing = false
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            toolbar.onSearchQueryChanged()
                .debounce(2000)
                .onEach { text ->
                    viewModel.onLoadMore(text)
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

        }

        viewModel
            .dataFlow
            .onEach { it ->
                adapter.submitList(emptyList())
                adapter.submitList(it.map { PagingData.Content(it) } )
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    companion object {
        private const val ITEMS_SPACE = 40
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}