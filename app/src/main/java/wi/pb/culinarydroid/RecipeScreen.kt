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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(viewModel: MainViewModel, onBack: () -> Unit) {
    val recipe = viewModel.recipe
    if (recipe != null) {

        Log.d("RecipeScreen", "Recipe: $recipe")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Return to main screen button
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

            // Recipe image
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

            // Recipe information
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
            // Ingredients
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

            // Instructions steps
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

            // Additional information about the recipe
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
                }
            }
        }
    } else {
        Log.e("RecipeScreen", "Recipe is null")
        Text("Error loading recipe, check your internet connection")
    }
}

@Composable
fun AnalyzedInstructionCard(analyzedInstruction: AnalyzedInstruction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp)
    ) {
        // Display the name of the instruction
        Text(
            text = analyzedInstruction.name,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Display a list of steps
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
        // Display step number
        Text(
            text = "Step ${step.number}:",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Display step instruction content
        Text(
            text = step.step,
            style = TextStyle(
                fontSize = 20.sp,
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Display ingredients
        if (step.ingredients.isNotEmpty()) {
            Text(
                text = "Ingredients:",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            step.ingredients.forEach { ingredient ->
                Text(
                    text = "- ${ingredient.name}",
                    style = TextStyle(
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        // Display Equipment
        if (step.equipment.isNotEmpty()) {
            Text(
                text = "Equipment:",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            step.equipment.forEach { equipment ->
                Text(
                    text = "- ${equipment.name}",
                    style = TextStyle(
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        // Display preparation length (if available)
        step.length?.let {
            Text(
                text = "Cooking Time: ${it.number} ${it.unit}",
                style = TextStyle(
                    fontSize = 20.sp,
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
