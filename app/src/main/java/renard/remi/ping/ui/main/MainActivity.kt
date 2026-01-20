package renard.remi.ping.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import renard.remi.ping.ui.home.HomeScreen
import renard.remi.ping.ui.home.HomeViewModel
import renard.remi.ping.ui.movie_details.MovieDetailsScreen
import renard.remi.ping.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                val navController = rememberNavController()

                Scaffold { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "Home"
                    ) {
                        composable(route = "Home") {
                            val homeViewModel: HomeViewModel = viewModel()
                            val state by homeViewModel.state.collectAsStateWithLifecycle()

                            HomeScreen(
                                modifier = Modifier.padding(paddingValues),
                                onEvent = homeViewModel::onEvent,
                                state = state,
                                onMovieClicked = { movie ->
                                    val encodedUrl = Uri.encode(movie.imgPoster)
                                    navController.navigate("MovieDetails/$encodedUrl")
                                }
                            )
                        }
                        composable(
                            route = "MovieDetails/{pictureUrl}",
                            arguments = listOf(navArgument("pictureUrl") {
                                type = NavType.StringType
                            })
                        ) { navBackStackEntry ->
                            val pictureUrl = navBackStackEntry.arguments?.getString("pictureUrl")
                                ?.let { Uri.decode(it) } ?: ""

                            MovieDetailsScreen(
                                modifier = Modifier.padding(paddingValues),
                                pictureUrl = pictureUrl
                            )
                        }
                    }
                }
            }
        }
    }
}

