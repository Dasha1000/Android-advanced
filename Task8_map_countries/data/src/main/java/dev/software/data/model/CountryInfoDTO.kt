package dev.software.data.model

data class CountryInfoDTO(
    val area: Float,
    val population: Float,
    val capital: Array<String>,
    val region: String,
    val latlng: Array<Float>
)

