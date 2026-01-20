package renard.remi.ping.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import renard.remi.ping.domain.model.Movie

class HomeViewModel() : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    init {
        println("HomeViewModel initialized")
    }

    fun onEvent(event: HomeUIEvent) {
        when (event) {
            is HomeUIEvent.ChangeSearchText -> updateSearchText(event.text)
            is HomeUIEvent.SubmitSearch -> searchMovie()
        }
    }

    private fun updateSearchText(searchText: String) {
        _state.update {
            it.copy(searchText = searchText)
        }
    }

    private fun searchMovie() {
        // TODO fetch from network
        _state.update {
            it.copy(
                movies = listOf(
                    Movie(
                        title = "Un titre de film",
                        imageUrl = "https://projetcartylion.fr/wp-content/uploads/2020/08/Placeholder.png"
                    )
                )
            )
        }
    }
}

sealed interface HomeUIEvent {
    data class ChangeSearchText(val text: String) : HomeUIEvent
    data object SubmitSearch : HomeUIEvent
}
