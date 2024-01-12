package wi.pb.culinarydroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import wi.pb.culinarydroid.ui.theme.CulinaryDroidTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinaryDroidTheme {
                val viewModel: MainViewModel = viewModel()
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "main_screen"
                ) {
                    composable("main_screen") {
                        MainScreen(
                            onSearch = { searchParameters ->
                                viewModel.viewModelScope.launch {
                                    val recipe = viewModel.getRandomRecipes(searchParameters.includeTags, searchParameters.excludeTags)
                                    viewModel.recipes = recipe
                                    if (recipe != null) {
                                        navController.navigate("recipe_screen")
                                    } else {
                                        Log.e("error recipe", "Recipe is null in RandomRecipeScreen")
                                    }
                                }
                            },
                            onNavigateToWheelScreen = {
                                navController.navigate("wheel_screen")
                            }
                        )
                    }

                    composable("recipe_screen") {
                        val recipe = viewModel.recipes
                        if (recipe != null) {
                            RecipeScreen(
                                recipe = recipe,
                                onBack = { navController.popBackStack() }
                            )
                        } else {
                            Log.e("MainActivity", "Recipe is null")
                            Text("Error loading recipe")
                        }
                    }

                    composable("wheel_screen") {
                        WheelScreen()
                    }
                }
            }
        }
    }
}



