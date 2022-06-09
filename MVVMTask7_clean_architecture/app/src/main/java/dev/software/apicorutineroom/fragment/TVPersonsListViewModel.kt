package dev.software.apicorutineroom.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.software.domain.usecase.GetPersonsUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class TVPersonsListViewModel(
    private val getPersonsUseCase: GetPersonsUseCase
) : ViewModel() {


    private var searchFlow = MutableSharedFlow<LoadState>(
        replay = 1, extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )


    private var query = ""

    fun onSearch(query: String) {
        this.query = query
        searchFlow.tryEmit(LoadState.LOAD_MORE)
    }

    fun onRefresh() {
        searchFlow.tryEmit(LoadState.REFRESH)
    }

    val dataFlow = searchFlow
        .map {
            getPersonsUseCase.invoke(query)
                .fold(
                    onSuccess = {
                        getPersonsUseCase.insertTVPersons(it)
                        it
                    },
                    onFailure = {
                        emptyList()
                    }
                )
        }
        .onStart {
            emit(getPersonsUseCase.invoke(PAGE_SIZE, 0).getOrDefault(emptyList()))
        }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            replay = 1
        )

    companion object {
        private const val PAGE_SIZE = 2
    }

    enum class LoadState {
        LOAD_MORE,
        REFRESH
    }
}