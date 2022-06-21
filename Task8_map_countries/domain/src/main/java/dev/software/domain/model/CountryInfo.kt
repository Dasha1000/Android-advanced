package dev.software.domain.model

data class CountryInfo(
    val area: Float,
    val population: Float,
    val capital: Array<String>,
    val region: String,
    val coordinates: Array<Float>
)
