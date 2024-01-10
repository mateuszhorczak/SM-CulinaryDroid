package wi.pb.culinarydroid

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Przycisk powrotu
        item {
            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Back to Search")
            }
        }

        // Obrazek przepisu
        item {
            Image(
                painter = rememberAsyncImagePainter(model = recipe.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        // Informacje o przepisie
        item {
            Text(
                text = recipe.title,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
        // Składniki
        item {
            Text(
                text = "Ingredients:",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            recipe.extendedIngredients?.let { ingredients ->
                ingredients.forEach { ingredient ->
                    Text(
                        text = "- ${ingredient.original}",
                        style = TextStyle(
                            fontSize = 20.sp,
                        ),
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
            }
        }

        // Wyświetlanie kroków instrukcji
        item {
            Text(
                text = "Instructions:",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            recipe.analyzedInstructions?.let { instructions ->
                instructions.forEach { analyzedInstruction ->
                    AnalyzedInstructionCard(analyzedInstruction)
                }
            }
        }

        // Dodatkowe informacje o przepisie
        item {
            Text(
                text = "Additional Information:",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Column {
                Text(
                    text = "Servings: ${recipe.servings}",
                    style = TextStyle(
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Text(
                    text = "Ready in minutes: ${recipe.readyInMinutes}",
                    style = TextStyle(
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Text(
                    text = "Health Score: ${recipe.healthScore}",
                    style = TextStyle(
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                // ... Dodaj więcej informacji, jeśli są dostępne
            }
        }
    }
}

@Composable
fun AnalyzedInstructionCard(analyzedInstruction: AnalyzedInstruction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp)
    ) {
        // Wyświetlanie nazwy instrukcji
        Text(
            text = analyzedInstruction.name,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Wyświetlanie listy kroków
        analyzedInstruction.steps.forEach { step ->
            InstructionStepCard(step)
        }
    }
}

@Composable
fun InstructionStepCard(step: InstructionStep) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        // Wyświetlanie numeru kroku
        Text(
            text = "Step ${step.number}:",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColorFor(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Wyświetlanie treści kroku
        Text(
            text = step.step,
            style = TextStyle(
                fontSize = 20.sp,
                color = contentColorFor(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Wyświetlanie składników
        if (step.ingredients.isNotEmpty()) {
            Text(
                text = "Ingredients:",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColorFor(MaterialTheme.colorScheme.background)
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            step.ingredients.forEach { ingredient ->
                Text(
                    text = "- ${ingredient.name}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = contentColorFor(MaterialTheme.colorScheme.background)
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        // Wyświetlanie sprzętu
        if (step.equipment.isNotEmpty()) {
            Text(
                text = "Equipment:",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColorFor(MaterialTheme.colorScheme.background)
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            step.equipment.forEach { equipment ->
                Text(
                    text = "- ${equipment.name}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = contentColorFor(MaterialTheme.colorScheme.background)
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        // Wyświetlanie długości przygotowania (jeśli dostępne)
        step.length?.let {
            Text(
                text = "Cooking Time: ${it.number} ${it.unit}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = contentColorFor(MaterialTheme.colorScheme.background)
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
