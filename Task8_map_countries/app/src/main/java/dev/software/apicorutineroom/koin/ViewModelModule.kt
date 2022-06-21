package dev.software.apicorutineroom.koin

import dev.software.apicorutineroom.fragment.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { SearchCountryViewModel() }
    viewModel { (name: String) -> CountryViewModel(get(), name) }
}