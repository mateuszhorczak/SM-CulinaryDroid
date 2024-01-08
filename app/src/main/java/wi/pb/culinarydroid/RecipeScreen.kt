package wi.pb.culinarydroid

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(recipe: Recipe, onBack: () -> Unit) {
    Log.d("RecipeScreen", "Recipe: $recipe")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Przycisk powrotu
        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Back to Search")
        }

        // Obrazek przepisu
        Image(
            painter = rememberAsyncImagePainter(model = recipe.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        // Informacje o przepisie
        Text(
            text = recipe.title,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )

        // Składniki
        Text(
            text = "Ingredients:",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )
        recipe.extendedIngredients?.let { ingredients ->
            Column {
                ingredients.forEach { ingredient ->
                    Text(
                        text = "- ${ingredient.original}",
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
            }
        }

        // Instrukcje
        Text(
            text = "Instructions:",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )
        recipe.analyzedInstructions?.let { instructions ->
            Column {
                instructions.forEachIndexed { index, instruction ->
                    Text(
                        text = "${index + 1}. ${instruction.steps.firstOrNull()?.step ?: ""}",
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
            }
        }

        // Dodatkowe informacje o przepisie
        Text(
            text = "Additional Information:",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Column {
            Text(
                text = "Servings: ${recipe.servings}",
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Text(
                text = "Ready in minutes: ${recipe.readyInMinutes}",
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Text(
                text = "Health Score: ${recipe.healthScore}",
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            // ... Dodaj więcej informacji, jeśli są dostępne
        }
    }
}

