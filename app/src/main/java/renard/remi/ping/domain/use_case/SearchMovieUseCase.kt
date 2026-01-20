package renard.remi.ping.domain.use_case

import renard.remi.ping.data.repository.MovieRepositoryImpl
import renard.remi.ping.domain.repository.MovieRepository

data class SearchMovieUseCase(
    // Next step : Use DI
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) {
    suspend operator fun invoke(name: String) = movieRepository.findMovie(name = name)
}