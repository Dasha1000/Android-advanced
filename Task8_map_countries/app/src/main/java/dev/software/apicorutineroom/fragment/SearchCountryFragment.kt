package dev.software.apicorutineroom.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dev.software.apicorutineroom.databinding.FragmentSearchCountryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCountryFragment : Fragment() {
    private var _binding: FragmentSearchCountryBinding? = null
    private val binding: FragmentSearchCountryBinding
        get() = requireNotNull(_binding)

    private val viewModel by viewModel<SearchCountryViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchCountryBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

       binding.buttonAddCountry.setOnClickListener {
           findNavController().navigate(
               SearchCountryFragmentDirections.toMap(
                   binding.editText.text.toString()
               )
           )
       }
        binding.toolbar.setupWithNavController(findNavController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
