package wi.pb.culinarydroid

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class ApiClient {
    private val client = OkHttpClient()

    suspend fun getRandomRecipes(apiKey: String, number: Int, includeTags: String, excludeTags: String): String {
        return withContext(Dispatchers.IO) {
            // Tutaj umieść kod operacji sieciowej, np. użyj klienta OkHttpClient
            // Upewnij się, że ta funkcja jest oznaczona jako suspend.

            val url = "https://api.spoonacular.com/recipes/random"
            val response = client.newCall(
                Request.Builder()
                    .url("$url?apiKey=$apiKey&number=$number&tags=$includeTags&excludeTags=$excludeTags")
                    .build()
            ).execute()

            return@withContext if (response.isSuccessful) {
                response.body?.string() ?: "Empty response body"
            } else {
                "Error: ${response.code} - ${response.message}"
            }
        }
    }
}

