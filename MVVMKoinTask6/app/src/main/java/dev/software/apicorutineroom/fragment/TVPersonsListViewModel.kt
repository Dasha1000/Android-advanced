package dev.software.apicorutineroom.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.software.apicorutineroom.TVPersonsRepository
import dev.software.apicorutineroom.database.AppDatabase
import dev.software.apicorutineroom.model.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class TVPersonsListViewModel(
    private val tvPersonsRepository: TVPersonsRepository,
    private val appDatabase: AppDatabase
) : ViewModel() {

    private var loadMoreFlow = MutableSharedFlow<LoadState>(
        replay = 1, extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    private var query = ""

    fun onLoadMore(query: String) {
        this.query = query
        loadMoreFlow.tryEmit(LoadState.LOAD_MORE)
    }

    fun onRefresh() {
        loadMoreFlow.tryEmit(LoadState.REFRESH)
    }

    private var mutableListOfDB = mutableListOf<Persons>()
    private var mutableListOfPersons = mutableListOf<TVPersons>()

    val dataFlow = loadMoreFlow

        .map {
            runCatching {
                tvPersonsRepository.getPersons(query)
            }
                .fold(
                    onSuccess = { it ->
                        it.forEach {
                            mutableListOfDB.add(
                                Persons(
                                    id = it.person.id,
                                    url = it.person.url ?: "N/A",
                                    name = it.person.name ?: "N/A",
                                    original = it.person.image?.original ?: "N/A"
                                )
                            )
                        }
                        appDatabase.DaoDB().insertTVPersons(
                            mutableListOfDB
                        )
                        it
                    },
                    onFailure = {
                        emptyList()
                    }
                )
        }
        .onStart {

            appDatabase.DaoDB().getTVPersons(PAGE_SIZE, 0).forEach {

                mutableListOfPersons.add(
                    TVPersons(
                        Person(it.id, it.url, it.name, Image(it.original))
                    )
                )

            }
            emit(mutableListOfPersons)

        }.shareIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            replay = 1
        )

    companion object {
        private const val PAGE_SIZE = 5
    }

    enum class LoadState {
        LOAD_MORE,
        REFRESH
    }
}