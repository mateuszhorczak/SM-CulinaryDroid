package wi.pb.culinarydroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import wi.pb.culinarydroid.ui.theme.CulinaryDroidTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinaryDroidTheme {
                val viewModel: MainViewModel = viewModel()
                var showRecipeScreen by remember { mutableStateOf(false) }

                if (showRecipeScreen) {
                    val recipe = viewModel.recipes
                    if (recipe != null) {
                        Log.d("MainActivity", "Recipe received: $recipe")
                        RecipeScreen(
                            recipe = recipe,
                            onBack = { showRecipeScreen = false }
                        )
                    } else {
                        Log.e("MainActivity", "Recipe is null")
                        Text("Error loading recipe")
                    }
                } else {
                    MainScreen {
                        viewModel.viewModelScope.launch {
                            val recipe = viewModel.getRandomRecipes(it.includeTags, it.excludeTags)
                            viewModel.recipes = recipe
                            if (recipe != null) {
                                showRecipeScreen = true
                            } else {
                                Log.e("error recipe", "Recipe is null in RandomRecipeScreen")
                            }
                        }
                    }
                }
            }
        }
    }
}


