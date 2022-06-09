package dev.software.apicorutineroom.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.software.apicorutineroom.TVPersonsRepository
import dev.software.apicorutineroom.model.PersonInfo
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class DetailsPersonsViewModel(
    private val tvPersonsRepository: TVPersonsRepository,
    private val id: Long
) : ViewModel() {

    val personsInfo: Flow<PersonInfo> =
        flow {
            val detailList = tvPersonsRepository.getPersonInfo(id, "castcredits")
            emit(detailList)
        }
            .shareIn(
                viewModelScope,
                started = SharingStarted.Eagerly,
                replay = 1
            )


    private val _personsTVShowClickFlow = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val personsTVShowClickFlow = _personsTVShowClickFlow.asSharedFlow()


    val navigateToTVShow = personsTVShowClickFlow
        .flatMapLatest {
            personsInfo
        }
        .map {
            it._embedded.castcredits
        }

    fun onClick() {
        _personsTVShowClickFlow.tryEmit(Unit)
    }
}
