package renard.remi.ping.ui.movie_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    pictureUrl: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = pictureUrl,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieUrlScreenPreview() {
    MovieDetailsScreen(
        pictureUrl = ""
    )
}
