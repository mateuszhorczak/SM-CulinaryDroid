package wi.pb.culinarydroid

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.IOException


class MainViewModel : ViewModel() {

    var recipes by mutableStateOf<Recipe?>(null)

    suspend fun getRandomRecipes(includeTags: String, excludeTags: String): Recipe? {
        Log.d("MainViewModel", "Getting random recipes. Include tags: $includeTags, Exclude tags: $excludeTags")
        return try {
            val apiClient = ApiClient()
            apiClient.getRandomRecipes("34cfa6fd6e2c40c2ae274ff7117435c5", 1, includeTags, excludeTags)
        } catch (e: IOException) {
            Log.e("MainViewModel", "IOException: $e")
            null
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error: $e")
            null
        }
    }
}


