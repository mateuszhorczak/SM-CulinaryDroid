package wi.pb.culinarydroid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel : ViewModel() {

    var recipes by mutableStateOf("")
        private set

    fun getRandomRecipes(includeTags: String, excludeTags: String) {
        viewModelScope.launch {
            recipes = try {
                val apiClient = ApiClient()
                apiClient.getRandomRecipes("34cfa6fd6e2c40c2ae274ff7117435c5", 1, includeTags, excludeTags)
            } catch (e: IOException) {
                "Error fetching recipes: ${e.message}"
            }
        }
    }
}
