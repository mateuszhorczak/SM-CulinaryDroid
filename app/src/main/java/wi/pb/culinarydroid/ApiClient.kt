package wi.pb.culinarydroid

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

class ApiClient {
    private val client = OkHttpClient()

    suspend fun getRandomRecipe(
        apiKey: String,
        number: Int,
        includeTags: String,
        excludeTags: String
    ): Recipe? {
        return withContext(Dispatchers.IO) {
            val url = "https://api.spoonacular.com/recipes/random"
            val response = client.newCall(
                Request.Builder()
                    .url("$url?apiKey=$apiKey&number=$number&tags=$includeTags&excludeTags=$excludeTags")
                    .build()
            ).execute()


            return@withContext if (response.isSuccessful) {
                val jsonString = response.body?.string()
                Log.d("ApiClient", "Received JSON: $jsonString")
                parseRecipeFromJson(jsonString)
            } else {
                Log.e("ApiClient", "Request failed with code ${response.code}")
                null
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun parseRecipeFromJson(jsonString: String?): Recipe? {
        if (jsonString.isNullOrBlank()) {
            return null
        }

        return try {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            Log.d("ApiClient", "Deserializacja JSON: $jsonString")
            val recipeWrapper = json.decodeFromString<RecipeWrapper>(jsonString)
            Log.d("ApiClient", "RecipeWrapper: $recipeWrapper")
            val recipe = recipeWrapper.recipes?.firstOrNull()
            Log.d("ApiClient", "Recipe: $recipe")

//            val recipeWrapper = json.decodeFromString<RecipeWrapper>(jsonString)
            recipeWrapper.recipes?.firstOrNull()
        } catch (e: Exception) {
            Log.e("ApiClient", "Error parsing JSON: $jsonString", e)
            null
        }
    }


}
