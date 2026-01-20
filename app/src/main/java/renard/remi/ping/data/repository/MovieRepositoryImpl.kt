package renard.remi.ping.data.repository

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import renard.remi.ping.data.network.ApiClient
import renard.remi.ping.data.network.ApiService
import renard.remi.ping.data.network.toDomain
import renard.remi.ping.domain.repository.MovieRepository

class MovieRepositoryImpl(
    // Next step : Use DI
    private val apiService: ApiService = ApiClient.create()
) : MovieRepository {

    override suspend fun findMovie(name: String) = flow {
        val response = apiService.searchMovie(search = name)
        emit(Result.success(response.toDomain()))
    }.catch { throwable ->
        emit(Result.failure(throwable))
    }
}