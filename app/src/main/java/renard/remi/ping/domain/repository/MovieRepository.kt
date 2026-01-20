package renard.remi.ping.domain.repository

import kotlinx.coroutines.flow.Flow
import renard.remi.ping.domain.model.Movie

interface MovieRepository {

    suspend fun findMovie(name: String): Flow<Result<List<Movie>>>
}