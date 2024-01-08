package wi.pb.culinarydroid
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.LocalTextStyle
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.rememberAsyncImagePainter
//import wi.pb.culinarydroid.ui.theme.CulinaryDroidTheme
//
//
//@Composable
//fun RandomRecipeDetailActivity(recipe: Recipe, onBackPressed: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Back button
//        IconButton(onClick = onBackPressed) {
//            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Recipe image
//        Image(
//            painter = rememberAsyncImagePainter(model = recipe.image),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .clip(shape = MaterialTheme.shapes.medium),
//            contentScale = ContentScale.Crop
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Recipe title
//        Text(
//            text = recipe.title,
//            style = LocalTextStyle.current.copy(
//                fontSize = 30.sp,
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Ingredients title
//        Text(
//            text = "Ingredients:",
//            style = LocalTextStyle.current.copy(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Ingredients list
//        recipe.extendedIngredients?.let { extendedIngredients ->
//            LazyColumn {
//                items(extendedIngredients) { ingredient ->
//                    Text(
//                        text = "- ${ingredient.name}: ${ingredient.amount} ${ingredient.unit}",
//                        style = LocalTextStyle.current.copy(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Normal
//                        ),
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Instructions title
//        Text(
//            text = "Instructions:",
//            style = LocalTextStyle.current.copy(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Instructions list
//        recipe.analyzedInstructions?.let { analyzedInstructions ->
//            LazyColumn {
//                items(analyzedInstructions) { instruction ->
//                    Text(
//                        text = "${instruction.name}:",
//                        style = LocalTextStyle.current.copy(
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(bottom = 4.dp)
//                    )
//
//                    // Nested LazyColumn for steps
//                    LazyColumn {
//                        items(instruction.steps) { step ->
//                            Text(
//                                text = "${step.number}. ${step.step}",
//                                style = LocalTextStyle.current.copy(
//                                    fontSize = 16.sp,
//                                    fontWeight = FontWeight.Normal
//                                ),
//                                modifier = Modifier.fillMaxWidth()
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun RandomRecipeDetailActivityPreview() {
//    CulinaryDroidTheme {
//        RandomRecipeDetailActivity(
//            recipe = sampleRecipe(),
//            onBackPressed = { /* Handle back button press */ }
//        )
//    }
//}
