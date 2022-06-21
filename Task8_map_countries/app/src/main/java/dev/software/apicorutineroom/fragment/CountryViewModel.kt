package dev.software.apicorutineroom.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.software.domain.model.CountryInfo
import dev.software.domain.usecase.GetCountryInfoUseCase
import kotlinx.coroutines.flow.*

class CountryViewModel(
    private val getCountryInfoUseCase: GetCountryInfoUseCase,
    private val name: String,
) : ViewModel() {

    val countryInfoInfo: Flow<List<CountryInfo>> =
        flow {
            getCountryInfoUseCase(name)
                .fold(
                    onSuccess = {
                        getCountryInfoUseCase.insertCountries(it)
                        emit(it)
                        //emit(getCountryInfoUseCase.invoke(10,0).getOrDefault(emptyList()))
                    },
                    onFailure = {
                        emptyList<CountryInfo>()
                    }
                )
        }
            .shareIn(
                viewModelScope,
                started = SharingStarted.Eagerly,
                replay = 1
            )
}