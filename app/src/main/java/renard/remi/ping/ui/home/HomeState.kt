package renard.remi.ping.ui.home

import renard.remi.ping.domain.model.Movie

data class HomeState(
    val searchText: String? = null,
    val movies: List<Movie> = emptyList()
)