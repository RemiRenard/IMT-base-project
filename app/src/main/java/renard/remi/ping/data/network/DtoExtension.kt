package renard.remi.ping.data.network

import renard.remi.ping.data.network.dto.response.MovieResponse
import renard.remi.ping.domain.model.Movie

fun MovieResponse.toDomain(): List<Movie> {
    return this.description.map {
        Movie(
            title = it.title,
            year = it.year,
            imdbId = it.imdbId,
            rank = it.rank,
            actors = it.actors,
            aka = it.aka,
            imdbUrl = it.imdbUrl,
            imdbIv = it.imdbIv,
            imgPoster = it.imgPoster,
            photoWidth = it.photoWidth,
            photoHeight = it.photoHeight
        )
    }
}