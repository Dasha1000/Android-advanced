package dev.software.apicorutineroom.model

data class PersonInfo(
    val id: Long,
    val name: String?,
    val url: String?,
    val birthday: String?,
    val image: Image?,
    val _embedded: Embedded
)

data class Embedded(
    val castcredits: List<CastCredits>
)

data class CastCredits(
    val _links: Links
)

data class Links(
    val show: Show
)

data class Show(
    val href: String
)