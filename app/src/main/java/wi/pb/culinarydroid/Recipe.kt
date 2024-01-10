package wi.pb.culinarydroid

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class RecipeWrapper(
    val recipes: List<Recipe>?
)

@Serializable
class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String,
    val extendedIngredients: List<@Contextual ExtendedIngredient>?,
    val analyzedInstructions: List<@Contextual AnalyzedInstruction>?,
    val servings: Int,
    val readyInMinutes: Int,
    val healthScore: Double,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val veryHealthy: Boolean,
    val cheap: Boolean,
    val veryPopular: Boolean,
    val sustainable: Boolean,
    val lowFodmap: Boolean,
    val weightWatcherSmartPoints: Double,
    val gaps: String,
    val preparationMinutes: Double,
    val cookingMinutes: Double,
    val aggregateLikes: Double,
    val creditsText: String,
    val license: String? = "",
    val sourceName: String,
    val pricePerServing: Double,
    val sourceUrl: String,
    val summary: String,
    val cuisines: List<String>,
    val dishTypes: List<String>,
    val diets: List<String>,
    val occasions: List<String>,
    val instructions: String,
    val originalId: Int?,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,


    )

@Serializable
class ExtendedIngredient(
    val id: Int,
    val name: String,
    val original: String,
    val aisle: String,
    val image: String,
    val consistency: String,
    val amount: Double,
    val unit: String,
    val meta: List<String>,
    val measures: Measures
)

@Serializable
class Measures(
    val us: Measure,
    val metric: Measure
)

@Serializable
class Measure(
    val amount: Double,
    val unitShort: String,
    val unitLong: String
)

@Serializable
class AnalyzedInstruction(
    val name: String,
    val steps: List<InstructionStep>
)

@Serializable
class InstructionStep(
    val number: Int,
    val step: String,
    val ingredients: List<Ingredient>,
    val equipment: List<Equipment>,
    val length: Length? = null
)

@Serializable
class Ingredient(
    val id: Int,
    val name: String,
    val localizedName: String,
    val image: String
)

@Serializable
class Equipment(
    val id: Int,
    val name: String,
    val localizedName: String,
    val image: String
)

@Serializable
class Length(
    val number: Int,
    val unit: String
)


fun sampleRecipe(): Recipe {
    return Recipe(
        id = 638533,
        title = "Chile Underground's Texas Caviar",
        image = "https://spoonacular.com/recipeImages/638533-556x370.jpg",
        imageType = "jpg",
        extendedIngredients = emptyList(),
        analyzedInstructions = emptyList(),
        servings = 8,
        readyInMinutes = 180,
        healthScore = 45.0,
        vegetarian = true,
        vegan = true,
        glutenFree = true,
        dairyFree = true,
        veryHealthy = false,
        cheap = false,
        veryPopular = false,
        sustainable = false,
        lowFodmap = false,
        weightWatcherSmartPoints = 6.0,
        gaps = "no",
        preparationMinutes = -1.0,
        cookingMinutes = -1.0,
        aggregateLikes = 4.0,
        creditsText = "Foodista.com – The Cooking Encyclopedia Everyone Can Edit",
        license = "CC BY 3.0",
        sourceName = "Foodista",
        pricePerServing = 140.19,
        sourceUrl = "http://www.foodista.com/recipe/KTCLZPKR/chile-undergrounds-texas-caviar",
        summary = "Chile Underground's Texas Caviar summary",
        cuisines = emptyList(),
        dishTypes = emptyList(),
        diets = emptyList(),
        occasions = emptyList(),
        instructions = "",
        originalId = null,
        spoonacularScore = 84.78133392333984,
        spoonacularSourceUrl = "https://spoonacular.com/chile-undergrounds-texas-caviar-638533"
    )
}



