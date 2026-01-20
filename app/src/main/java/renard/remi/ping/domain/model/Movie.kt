package renard.remi.ping.domain.model

data class Movie(
    val title: String? = null,
    val year: Int? = null,
    val imdbId: String? = null,
    val rank: Int? = null,
    val actors: String? = null,
    val aka: String? = null,
    val imdbUrl: String? = null,
    val imdbIv: String? = null,
    val imgPoster: String? = null,
    val photoWidth: Int? = null,
    val photoHeight: Int? = null
)