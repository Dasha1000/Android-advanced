package dev.software.apicorutineroom.retrofit

import dev.software.apicorutineroom.model.PersonInfo
import dev.software.apicorutineroom.model.PersonTVShowInfo
import dev.software.apicorutineroom.model.TVPersons
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVPersonsApi {
    @GET("search/people")
    suspend fun getTVPersons(
        @Query("q") q: String,
    ): List<TVPersons>

    @GET("people/{id}")
    suspend fun getPersonInfo(
        @Path("id") id: Long,
        @Query("embed") embed: String
    ): PersonInfo

    @GET("shows/{id}")
    suspend fun getPersonTVShowInfo(
        @Path("id") id: Long
    ): PersonTVShowInfo
}

// https://api.tvmaze.com/search/shows?q=girl - it will be on top btn soon
// https://api.tvmaze.com/search/people?q=vasia - it is
// https://api.tvmaze.com/people/id?embed=castcredits - it is