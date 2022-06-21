package dev.software.apicorutineroom.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.software.domain.model.PersonInfo
import dev.software.domain.usecase.GetPersonInfoUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*


class DetailsPersonsViewModel(
    private val getPersonInfoUseCase: GetPersonInfoUseCase,
    private val id: Long
) : ViewModel() {

    val personsInfo: Flow<PersonInfo> =
        flow {
            val detailList = getPersonInfoUseCase.invoke(id, "castcredits")
            detailList.map {
                emit(it)
            }
        }
            .shareIn(
                viewModelScope,
                started = SharingStarted.Eagerly,
                replay = 1
            )


    private val _personsTVShowClickFlow = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val personsTVShowClickFlow = _personsTVShowClickFlow.asSharedFlow()


    val navigateToTVShow = personsTVShowClickFlow
        .flatMapLatest {
            personsInfo
        }
        .map {
            it.href
        }

    fun onClick() {
        _personsTVShowClickFlow.tryEmit(Unit)
    }
}