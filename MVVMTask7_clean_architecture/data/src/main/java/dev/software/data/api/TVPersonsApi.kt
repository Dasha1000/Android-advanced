package dev.software.data.api

import dev.software.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TVPersonsApi {

    @GET("search/people")
    suspend fun getTVPersons(
        @Query("q") q: String,
    ): List<TVPersonsDTO>

    @GET("people/{id}")
    suspend fun getPersonInfo(
        @Path("id") id: Long,
        @Query("embed") embed: String
    ): PersonInfoDTO

    @GET("shows/{id}")
    suspend fun getPersonTVShowInfo(
        @Path("id") id: Long
    ): PersonTVShowInfoDTO
}

// https://api.tvmaze.com/search/shows?q=girl - it will be on top btn soon
// https://api.tvmaze.com/search/people?q=vasia - it is
// https://api.tvmaze.com/people/id?embed=castcredits - it is