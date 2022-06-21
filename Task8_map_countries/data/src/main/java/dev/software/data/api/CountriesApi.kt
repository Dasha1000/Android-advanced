package dev.software.data.api

import dev.software.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CountriesApi {

    @GET("v3.1/name/{name}")
    suspend fun getCountryInfo(
        @Path("name") name: String
    ): List<CountryInfoDTO>
}

// https://restcountries.com/v3.1/name/{name}
// https://restcountries.com/v3.1/name/Belarus
//https://restcountries.com/v2/name/{name}