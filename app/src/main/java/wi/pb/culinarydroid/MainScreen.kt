package wi.pb.culinarydroid

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(onSearch: (SearchParameters) -> Unit) {
    var includeTags by remember { mutableStateOf("") }
    var excludeTags by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    var isWheelSpinning by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<String?>(null) }

    // Animacja obrotu koła
    val rotationAngle by animateFloatAsState(
        targetValue = if (isWheelSpinning) 360f else 0f,
        tween(
            durationMillis = 1500,
            easing = FastOutSlowInEasing
        ), label = "Rotation angleeeee"
    )

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

        // Przycisk do rozpoczęcia wyszukiwania
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


        // Funkcja do losowania opcji
        fun spinWheel() {
            isWheelSpinning = true

            // Symulacja losowania opcji po pewnym czasie (możesz dostosować)
            GlobalScope.launch {
                delay(1500) // Czas trwania animacji obrotu koła
                val options =
                    listOf("Option 1", "Option 2", "Option 3", "Option 4") // Dodaj swoje opcje
                selectedOption = options.random()
                isWheelSpinning = false
            }
        }


        // Grafika koła fortuny
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Tutaj umieść grafikę koła fortuny (np. obrazek) i zastosuj animację obrotu
            // Możesz użyć obiektu Image i zastosować rotationAngle jako Modifier w animowanym obiekcie

            // Przycisk do rozpoczęcia losowania
            Button(
                onClick = {
                    spinWheel()
                },
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

            // Wyświetlanie wylosowanej opcji
            selectedOption?.let {
                // Tutaj możesz umieścić kod do wyświetlania wylosowanej opcji, np. na dole ekranu
                Text(
                    text = "Selected Option: $it",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }
        }
    }
}

data class SearchParameters(
    val includeTags: String,
    val excludeTags: String
)
