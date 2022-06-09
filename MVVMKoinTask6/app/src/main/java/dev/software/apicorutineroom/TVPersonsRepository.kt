package dev.software.apicorutineroom

import dev.software.apicorutineroom.retrofit.TVPersonsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TVPersonsRepository(private val tvPersonsApi: TVPersonsApi) {

    suspend fun getPersons(q: String) = withContext(Dispatchers.IO) {
        tvPersonsApi.getTVPersons(q)
    }

    suspend fun getPersonInfo(id: Long, query: String) = withContext(Dispatchers.IO) {
        tvPersonsApi.getPersonInfo(id, query)
    }

    suspend fun getPersonTVShow(id: Long) = withContext(Dispatchers.IO) {
        tvPersonsApi.getPersonTVShowInfo(id)
    }
}