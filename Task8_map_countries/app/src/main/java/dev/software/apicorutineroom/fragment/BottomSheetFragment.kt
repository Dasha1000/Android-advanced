package dev.software.apicorutineroom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.software.apicorutineroom.databinding.FragmentBottomSheetBinding

class BottomSheetFragment(
    private val area: Float,
    private val population: Float,
    private val capital: Array<String>,
    private val region: String,
    private val coordinates: Array<Float>
) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "BottomSheetFragment"
    }

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding: FragmentBottomSheetBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBottomSheetBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            countryRegion.text = "Region: $region"
            countryArea.text = "Area: ${area}"
            countryCapital.text = "Capital: ${capital[0]}"
            countryPopulation.text = "Population: ${population}"
            countryCoordinates.text = "latitude: ${coordinates[0]}\nlongitude: ${coordinates[1]}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}