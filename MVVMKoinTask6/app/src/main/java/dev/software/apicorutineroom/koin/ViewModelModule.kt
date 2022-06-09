package dev.software.apicorutineroom.koin

import dev.software.apicorutineroom.fragment.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TVPersonsListViewModel(get(), get()) }
    viewModel { (id: Long) -> DetailsPersonsViewModel(get(), id) }
    viewModel { (list: Array<String>) -> PersonTVShowListViewModel(get(), list) }
}