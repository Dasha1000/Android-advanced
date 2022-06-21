package dev.software.apicorutineroom.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.software.apicorutineroom.TVPersonsRepository
import dev.software.apicorutineroom.model.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import java.lang.Math.min

class PersonTVShowListViewModel(
    private val tvPersonsRepository: TVPersonsRepository,
    private val list: Array<String>
) : ViewModel() {

    private var loadMoreFlow = MutableSharedFlow<LoadState>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private var curPage = 0
    private var isLoading = false

    fun onLoadMore() {
        loadMoreFlow.tryEmit(LoadState.LOAD_MORE)
    }

    fun onRefresh() {
        loadMoreFlow.tryEmit(LoadState.REFRESH)
    }

    var mutableList = mutableListOf<PersonTVShowInfo>()
    val limit = list.size


    val dataFlow = loadMoreFlow
        .filter { !isLoading }
        .onEach {
            if (it == LoadState.REFRESH) {
                mutableList.clear()
                curPage = 0
            }
            isLoading = true
        }
        .map {
            runCatching {

                var end = min(limit, curPage * PAGE_SIZE + PAGE_SIZE)

                for (i in curPage * PAGE_SIZE until end) {
                    mutableList.add(tvPersonsRepository.getPersonTVShow(list[i].toLong()))
                }
                curPage++
                mutableList

            }
                .apply { isLoading = false }.fold(
                    onSuccess = {
                        it
                    },
                    onFailure = {
                        emptyList()
                    }
                )
        }

        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            replay = 1
        )

    companion object {
        private const val PAGE_SIZE = 4

    }

    enum class LoadState {
        LOAD_MORE,
        REFRESH
    }
}