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
    val extendedIngredients: List<@Contextual ExtendedIngredient>?,
    val analyzedInstructions: List<@Contextual AnalyzedInstruction>?,
    val servings: Int,
    val readyInMinutes: Int,
    val healthScore: Double,
    )

@Serializable
class ExtendedIngredient(
    val id: Int,
    val name: String,
    val original: String,
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
)

@Serializable
class Equipment(
    val id: Int,
    val name: String,
)

@Serializable
class Length(
    val number: Int,
    val unit: String
)
