package dev.software.data.model

data class PersonInfoDTO(
    val id: Long,
    val name: String?,
    val url: String?,
    val birthday: String?,
    val image: ImageDTO?,
    val _embedded: EmbeddedDTO
)

data class EmbeddedDTO(
    val castcredits: List<CastCreditsDTO>
)

data class CastCreditsDTO(
    val _links: LinksDTO
)

data class LinksDTO(
    val show: ShowDTO
)

data class ShowDTO(
    val href: String
)