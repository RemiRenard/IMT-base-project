package renard.remi.ping.ui.home

import renard.remi.ping.domain.model.Movie

data class HomeState(
    val searchText: String? = null,
    val isLoading: Boolean? = false,
    val movies: List<Movie> = emptyList()
)