package wi.pb.culinarydroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun WheelScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit,
    onNavigateToRecipeScreen: (Recipe?) -> Unit
) {
    var rotationState by remember { mutableFloatStateOf(0f) }
    var rotation by remember { mutableFloatStateOf(0f) }

    val job = remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(Unit) {
        job.value = launch {
            var elapsedTime = 0L
            while (isActive && elapsedTime < 3000) {
                rotation += 5f
                rotationState = rotation % 360
                delay(16)
                elapsedTime += 16
            }

            // After 3 seconds, get the recipe from API
            val recipe = viewModel.getRandomRecipe("", "")

            // Update recipe status in viewModel
            viewModel.updateRecipe(recipe)

            // Open the RecipeScreen
            onNavigateToRecipeScreen(recipe)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            job.value?.cancel()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Recipe draw",
            fontSize = 31.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Magenta
        )

        Image(
            painter = painterResource(id = R.drawable.fortune_wheel),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
                .graphicsLayer(rotationZ = rotationState),
        )
    }
}


