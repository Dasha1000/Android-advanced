package dev.software.apicorutineroom.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dev.software.apicorutineroom.databinding.FragmentCountryMapBinding
import dev.software.data.services.LocationService
import dev.software.domain.model.CountryInfo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CountryMapFragment : Fragment() {
    private var _binding: FragmentCountryMapBinding? = null

    private val binding
        get() = requireNotNull(_binding)

    private val viewModel by viewModel<CountryViewModel> {
        parametersOf(args.countryName)
    }
    private val args by navArgs<CountryMapFragmentArgs>()

    private val locationService by inject<LocationService>()

    private var googleMap: GoogleMap? = null
    private var locationListener: LocationSource.OnLocationChangedListener? = null

    @SuppressLint("MissingPermission")
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { permissionGranted ->
        if (permissionGranted) {
            viewLifecycleOwner.lifecycleScope.launch {
                locationService.getCurrentLocation()?.let(::moveCameraToLocation)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCountryMapBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        viewModel
            .countryInfoInfo.onEach { listCountries ->
                listCountries.map { countryInfo ->
                    val addMarker = googleMap?.addMarker(
                        MarkerOptions()
                            .position(

                                LatLng(
                                    countryInfo.coordinates[0].toDouble(),
                                    countryInfo.coordinates[1].toDouble()
                                )
                            )

                    )
                    addMarker?.tag = countryInfo.capital
                    println("мега-важно->" + countryInfo)
                    googleMap?.setOnMarkerClickListener {
                        println("мега-важно 41->" + countryInfo)
                        onMarkerClick(requireNotNull(addMarker), countryInfo)
                    }


                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)


        locationService
            .getLocationFlow()
            .onEach {
                locationListener?.onLocationChanged(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.mapView.getMapAsync { map ->
            googleMap = map.apply {
                uiSettings.isCompassEnabled = true
                uiSettings.isZoomControlsEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }

            map.isMyLocationEnabled = hasLocationPermission()
            map.setLocationSource(object : LocationSource {
                override fun activate(listener: LocationSource.OnLocationChangedListener) {
                    locationListener = listener
                }

                override fun deactivate() {
                    locationListener = null
                }
            })
        }

        binding.mapView.onCreate(savedInstanceState)

    }

    private fun onMarkerClick(marker: Marker, countryInfo: CountryInfo): Boolean {

        BottomSheetFragment(
            countryInfo.area,
            countryInfo.population,
            countryInfo.capital,
            countryInfo.region,
            countryInfo.coordinates
        ).show(childFragmentManager, BottomSheetFragment.TAG)

        return false
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
        googleMap = null
        _binding = null
    }


    private fun moveCameraToLocation(location: Location) {
        val current = LatLng(location.latitude, location.longitude)
        googleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(current, 17f)
        )
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}