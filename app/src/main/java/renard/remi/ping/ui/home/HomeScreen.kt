package renard.remi.ping.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import renard.remi.ping.domain.model.Movie
import renard.remi.ping.ui.component.MovieCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onEvent: (HomeUIEvent) -> Unit = {},
    onMovieClicked: (movie: Movie) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = state.searchText ?: "",
            onValueChange = {
                onEvent(HomeUIEvent.ChangeSearchText(it))
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier.fillMaxWidth(0.5F),
            onClick = {
                onEvent(HomeUIEvent.SubmitSearch)
            }
        ) {
            Text(text = "Find movies")
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn {
            items(state.movies) { movie ->
                MovieCard(
                    movie = movie,
                    onClick = {
                        onMovieClicked.invoke(it)
                    }
                )
            }
        }
    }
    if (state.isLoading == true) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(),
        onMovieClicked = {}
    )
}
