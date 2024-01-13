package wi.pb.culinarydroid

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoiseAware
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(onSearch: (SearchParameters) -> Unit, onNavigateToWheelScreen: () -> Unit) {
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
                    onSearch(SearchParameters(includeTags, excludeTags))
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
                    onSearch(SearchParameters(includeTags, excludeTags))
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Button to start searching
        Button(
            onClick = {
                Log.d(
                    "RandomRecipeScreen",
                    "Search clicked. includeTags: $includeTags, excludeTags: $excludeTags"
                )
                onSearch(SearchParameters(includeTags, excludeTags))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Search Recipes")
        }


        // Fortune wheel draw button
        Button(
            onClick = onNavigateToWheelScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.NoiseAware,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Spin the Wheel")
        }
        val shake = ShakeDetector(MainActivity.getInstance(), onNavigateToWheelScreen)
    }
}

data class SearchParameters(
    val includeTags: String,
    val excludeTags: String
)
