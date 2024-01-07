package wi.pb.culinarydroid

data class Recipe(
    val title: String,
    val image: String?,
    val ingredients: List<Ingredient>?,
    val instructions: List<Instruction>?
)

data class Ingredient(
    val name: String,
    val amount: Double,
    val unit: String
)

data class Instruction(
    val number: Int,
    val step: String
)

fun sampleRecipe(): Recipe {
    return Recipe(
        title = "Sample Recipe",
        image = "https://example.com/sample.jpg",
        ingredients = listOf(
            Ingredient("Flour", 1.0, "cup"),
            Ingredient("Sugar", 0.5, "cup"),
            // Dodaj więcej składników według potrzeb
        ),
        instructions = listOf(
            Instruction(1, "Preheat the oven to 350°F."),
            Instruction(2, "Mix flour and sugar."),
            // Dodaj więcej instrukcji według potrzeb
        )
    )
}

