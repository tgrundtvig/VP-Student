package dk.viprogram.week10;

/**
 * Exam Project Proposal Template
 *
 * Fill in each section with your project design.
 * Be specific enough that someone else could implement from your proposal.
 *
 * Run tests to verify all sections are complete.
 */
public class ProjectProposal {

    // ==================== Project Overview ====================

    /**
     * Your project title.
     * Example: "Personal Recipe Manager"
     */
    public static final String PROJECT_TITLE =
            ""; // TODO: Fill in your project title

    /**
     * A one-paragraph description of your project.
     * What does it do? Who is it for? What problem does it solve?
     */
    public static final String PROJECT_DESCRIPTION =
            ""; // TODO: Describe your project

    /**
     * Why did you choose this project?
     * What interests you about it?
     */
    public static final String PROJECT_MOTIVATION =
            ""; // TODO: Explain your motivation

    // ==================== Entity Definitions ====================

    /**
     * List your main entities (domain objects).
     * Format:
     * "Entity1: description of what it represents
     *  - property1: type - description
     *  - property2: type - description
     *
     * Entity2: description
     *  - property1: type - description"
     *
     * Example:
     * "Recipe: A cooking recipe with ingredients and steps
     *  - id: String - unique identifier
     *  - title: String - recipe name
     *  - servings: int - number of servings
     *  - ingredients: List<Ingredient> - required ingredients
     *
     * Ingredient: A single ingredient with amount
     *  - name: String - ingredient name
     *  - amount: double - quantity needed
     *  - unit: String - measurement unit"
     */
    public static final String ENTITIES =
            ""; // TODO: Define your entities

    /**
     * Describe how your entities relate to each other.
     * Which entities reference which? How?
     *
     * Example: "Recipe contains a list of Ingredients (composition).
     *           Recipe belongs to a Category (by categoryId reference)."
     */
    public static final String ENTITY_RELATIONSHIPS =
            ""; // TODO: Describe relationships

    // ==================== Interface Specifications ====================

    /**
     * Define your View interface.
     * List all methods with brief descriptions.
     *
     * Format:
     * "ViewName
     *  - methodName(params): returnType - what it does"
     *
     * Example:
     * "RecipeView
     *  - showRecipeList(List<Recipe>): void - displays all recipes
     *  - showRecipeDetail(Recipe): void - shows one recipe in detail
     *  - promptForNewRecipe(): Recipe - gets input for new recipe
     *  - showError(String): void - displays error message"
     */
    public static final String VIEW_INTERFACE =
            ""; // TODO: Define your View interface

    /**
     * Define your Repository interface(s).
     * Include both generic operations and domain-specific queries.
     *
     * Example:
     * "RecipeRepository extends Repository<Recipe, String>
     *  - findByCategory(String categoryId): List<Recipe>
     *  - findByIngredient(String ingredientName): List<Recipe>
     *  - findFavorites(): List<Recipe>"
     */
    public static final String REPOSITORY_INTERFACES =
            ""; // TODO: Define your Repository interface(s)

    /**
     * Define any Strategy or other behavioral interfaces.
     * If you don't have any, explain why they aren't needed.
     *
     * Example:
     * "ScalingStrategy - for scaling recipe amounts
     *  - scale(Recipe, int newServings): Recipe - returns scaled recipe
     *
     * Implementations: LinearScaling, RoundedScaling"
     */
    public static final String OTHER_INTERFACES =
            ""; // TODO: Define other interfaces or explain why none needed

    // ==================== User Operations ====================

    /**
     * List 5+ operations users can perform.
     * For each, describe: what happens, which layers are involved.
     *
     * Format:
     * "1. Operation name
     *    - User does: ...
     *    - System does: View -> Controller -> Repository
     *    - Result: ..."
     *
     * Example:
     * "1. Add new recipe
     *    - User does: Selects 'New Recipe', enters details
     *    - System does: View.promptForNewRecipe() -> Controller validates
     *                   -> Repository.save(recipe)
     *    - Result: Recipe saved, confirmation shown"
     */
    public static final String USER_OPERATIONS =
            ""; // TODO: List user operations

    // ==================== Architecture ====================

    /**
     * Draw your architecture as ASCII art or text description.
     * Show: layers, interfaces, implementations, data flow.
     *
     * Example:
     * "┌─────────────────────────────────────────┐
     *  │              RecipeApp                  │
     *  │          (composition root)             │
     *  └─────────────────────────────────────────┘
     *                     │ creates
     *                     ▼
     *  ┌─────────────────────────────────────────┐
     *  │            RecipeController             │
     *  └─────────────────────────────────────────┘
     *       │                        │
     *       ▼                        ▼
     *  ┌──────────┐          ┌───────────────┐
     *  │RecipeView│          │RecipeRepository│
     *  │ (iface)  │          │   (iface)      │
     *  └──────────┘          └───────────────┘"
     */
    public static final String ARCHITECTURE_DIAGRAM =
            ""; // TODO: Draw your architecture

    // ==================== Testing Strategy ====================

    /**
     * How will you test your application?
     * What mock implementations will you need?
     *
     * Example:
     * "Testing approach:
     *  - MockRecipeView: queues user inputs, records displayed output
     *  - InMemoryRecipeRepository: HashMap-based, fast for tests
     *  - Tests cover: CRUD operations, validation, edge cases"
     */
    public static final String TESTING_APPROACH =
            ""; // TODO: Describe your testing strategy

    // ==================== Scope and Timeline ====================

    /**
     * What is the Minimum Viable Product (MVP)?
     * What must work for a successful demo?
     */
    public static final String MVP_FEATURES =
            ""; // TODO: Define MVP features

    /**
     * What features could you add if you have extra time?
     */
    public static final String STRETCH_GOALS =
            ""; // TODO: List optional features

    /**
     * What are the main risks or challenges you foresee?
     */
    public static final String RISKS_AND_CHALLENGES =
            ""; // TODO: Identify potential challenges
}
