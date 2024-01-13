package wi.pb.culinarydroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import wi.pb.culinarydroid.ui.theme.CulinaryDroidTheme
import java.util.UUID


class MainActivity : ComponentActivity() {
    companion object {
        private lateinit var instance: MainActivity
        fun getInstance(): MainActivity {
            return instance
        }

    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinaryDroidTheme {
                val viewModel: MainViewModel = viewModel()
                val navController = rememberNavController()
                var wheelScreenKey by remember { mutableStateOf(UUID.randomUUID().toString()) }
                NavHost(
                    navController = navController,
                    startDestination = "main_screen"
                ) {
                    composable("main_screen") {
                        MainScreen(
                            onSearch = { searchParameters ->
                                viewModel.viewModelScope.launch {
                                    val recipe = viewModel.getRandomRecipe(
                                        searchParameters.includeTags,
                                        searchParameters.excludeTags
                                    )
                                    viewModel.recipe = recipe
                                    if (recipe != null) {
                                        navController.navigate("recipe_screen")
                                    } else {
                                        Log.e(
                                            "error recipe",
                                            "Recipe is null in RandomRecipeScreen"
                                        )
                                    }
                                }
                            },
                            onNavigateToWheelScreen = {
                                // Generate a new unique key for WheelScreen
                                wheelScreenKey = UUID.randomUUID().toString()
                                navController.navigate("wheel_screen")
                            }
                        )
                    }

                    composable("recipe_screen") {
                        RecipeScreen(
                            viewModel = viewModel,
                            onBack = { navController.navigate("main_screen") }
                        )
                    }

                    composable("wheel_screen") {
                        WheelScreen(
                            viewModel = viewModel,
                            onBack = {
                                navController.navigate("main_screen")
                            },
                            onNavigateToRecipeScreen = { recipe ->
                                if (recipe != null) {
                                    navController.navigate("recipe_screen")
                                } else {
                                    Log.e("MainActivity", "Recipe is null")
                                }
                            },
                        )
                    }

                }
            }
        }
        instance = this

    }
}



