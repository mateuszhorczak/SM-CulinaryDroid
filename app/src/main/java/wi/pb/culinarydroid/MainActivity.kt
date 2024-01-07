package wi.pb.culinarydroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import wi.pb.culinarydroid.ui.theme.CulinaryDroidTheme



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinaryDroidTheme {
                val viewModel: MainViewModel = viewModel()
                RandomRecipeScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RandomRecipeScreen(viewModel: MainViewModel = viewModel()) {
    var includeTags by remember { mutableStateOf("") }
    var excludeTags by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = includeTags,
            onValueChange = { includeTags = it },
            label = { Text("Include Tags") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    viewModel.getRandomRecipes(includeTags, excludeTags)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = excludeTags,
            onValueChange = { excludeTags = it },
            label = { Text("Exclude Tags") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    viewModel.getRandomRecipes(includeTags, excludeTags)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Przycisk do rozpoczęcia wyszukiwania
        Button(
            onClick = {
                viewModel.getRandomRecipes(includeTags, excludeTags)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Search Recipes")
        }

        // Miejsce do wyświetlenia wyników
        Text(
            text = viewModel.recipes,
            modifier = Modifier.fillMaxWidth()
        )
    }
}




//@Composable
//fun getRecipes(includeTags: String, excludeTags: String): String {
//    return try {
//        val apiClient = ApiClient()
//        apiClient.getRandomRecipes("34cfa6fd6e2c40c2ae274ff7117435c5", 1, includeTags, excludeTags)
//    } catch (e: IOException) {
//        "Error fetching recipes: ${e.message}"
//    }
//}

@Composable
fun DefaultPreview() {
    MaterialTheme {
        RandomRecipeScreen()
    }
}
