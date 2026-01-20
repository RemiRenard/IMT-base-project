package renard.remi.ping.data.network

import renard.remi.ping.data.network.dto.response.MovieResponse
import renard.remi.ping.domain.model.Movie

fun MovieResponse.toDomain(): List<Movie> {
    return this.description.map {
        Movie(
            title = it.title,
            pictureUrl = it.imgPoster
        )
    }
}