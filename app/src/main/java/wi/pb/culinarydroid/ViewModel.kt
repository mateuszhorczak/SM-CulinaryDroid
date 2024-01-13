package wi.pb.culinarydroid

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.IOException


class MainViewModel : ViewModel() {

    var recipe by mutableStateOf<Recipe?>(null)

    suspend fun getRandomRecipe(includeTags: String, excludeTags: String): Recipe? {
        Log.d(
            "MainViewModel",
            "Getting random recipe. Include tags: $includeTags, Exclude tags: $excludeTags"
        )
        return try {
            val apiClient = ApiClient()
            recipe = apiClient.getRandomRecipe(
                "YOUR SPOONACULAR API KEY", // get it here https://spoonacular.com/food-api
                1,
                includeTags,
                excludeTags
            )
            recipe
        } catch (e: IOException) {
            Log.e("MainViewModel", "IOException: $e")
            null
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error: $e")
            null
        }
    }

    fun updateRecipe(recipe: Recipe?) {
        this.recipe = recipe
    }

}


