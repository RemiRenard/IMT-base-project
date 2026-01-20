package renard.remi.ping.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import renard.remi.ping.domain.use_case.SearchMovieUseCase

class HomeViewModel(
    // Next step : Use DI
    private val searchMovieUseCase: SearchMovieUseCase = SearchMovieUseCase()
) : ViewModel() {

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
        viewModelScope.launch {
            _state.value.searchText?.let {
                searchMovieUseCase(name = it).collect { result ->
                    result.fold(
                        onSuccess = { movies ->
                            _state.update { state ->
                                state.copy(movies = movies)
                            }
                        },
                        onFailure = {
                            _state.update { state ->
                                state.copy(movies = emptyList())
                            }
                        }
                    )
                }
            }
        }
    }
}

sealed interface HomeUIEvent {
    data class ChangeSearchText(val text: String) : HomeUIEvent
    data object SubmitSearch : HomeUIEvent
}
