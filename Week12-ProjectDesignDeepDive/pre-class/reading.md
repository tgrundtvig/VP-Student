# Week 12 Pre-Class Reading: Designing Complete Interfaces

## Introduction

In Week 11, you created an architecture overview with interface names and rough method lists.
This week, you make those interfaces production-ready. A production-ready interface has precise
method signatures, clear parameter types, meaningful return types, and JavaDoc that fully
describes the contract.

**Why does this matter?** Because a well-specified interface lets you:
- Write tests before any implementation exists
- Hand the interface to another developer and they can implement it
- Swap implementations without changing any calling code
- Reason about the system without reading implementation details

## Method Signatures: The Full Picture

A complete method signature has five parts:

```java
/**
 * [4. JavaDoc: what this method does, as a contract]
 *
 * @param categoryId [5. Parameter doc: what this parameter means]
 * @return [5. Return doc: what the return value represents]
 */
List<Recipe> findByCategory(String categoryId);
//[1]  [2]    [3]
// 1. Return type
// 2. Method name
// 3. Parameters with types
// 4. JavaDoc contract description
// 5. Parameter and return documentation
```

### Return Types Tell a Story

Choose return types carefully. Each type communicates something:

| Return Type | Meaning |
|------------|---------|
| `void` | Command: performs an action, returns nothing |
| `T` | Always returns a value (never null) |
| `Optional<T>` | Might return a value, might not (e.g., findById) |
| `List<T>` | Returns zero or more values |
| `boolean` | Yes/no result (e.g., exists, confirm) |
| `int` | Count or numeric result |

**Example: Repository methods and their return types**

```java
public interface RecipeRepository {
    // Command: save this recipe (create or update)
    void save(Recipe recipe);

    // Query: find by ID, might not exist
    Optional<Recipe> findById(String id);

    // Query: get all, always returns a list (possibly empty)
    List<Recipe> findAll();

    // Query: filter by category, returns matching recipes
    List<Recipe> findByCategory(String categoryId);

    // Command: delete by ID
    void delete(String id);

    // Query: check existence
    boolean existsById(String id);

    // Query: count all
    int count();
}
```

### Parameter Types Tell a Story Too

Use the most specific type that makes sense:

```java
// BAD: too generic, what kind of String? What does it represent?
List<Recipe> find(String value);

// GOOD: parameter name and type make the intent clear
List<Recipe> findByCategory(String categoryId);
List<Recipe> searchByTitle(String titleKeyword);
List<Recipe> findByServingsRange(int minServings, int maxServings);
```

### When to Use Records as Parameters

If a method needs many parameters, consider grouping them into a record:

```java
// Too many parameters
Recipe promptForNewRecipe(String title, String description, int servings,
    List<String> ingredientIds, String categoryId);

// Better: use the record itself (or a creation record)
Recipe promptForNewRecipe(List<Category> availableCategories);
// The View creates and returns a complete Recipe from user input
```

## JavaDoc for Interfaces: Describing the Contract

JavaDoc on interfaces describes **what** happens, not **how** it happens. This is the contract
that all implementations must honor.

### Interface-Level JavaDoc

```java
/**
 * Handles all user interaction for the recipe management application.
 *
 * Implementations of this interface may use any UI technology:
 * console text, JavaFX, web, or mock for testing.
 *
 * The controller calls display methods to show data and prompt methods
 * to collect user input. Implementations must handle their own
 * input validation and formatting.
 */
public interface RecipeView {
    // ... methods
}
```

### Method-Level JavaDoc

```java
/**
 * Displays a list of recipes for the user to browse.
 *
 * If the list is empty, the implementation should display an appropriate
 * message (e.g., "No recipes found") rather than showing nothing.
 *
 * The display format is implementation-specific: a console view might
 * show a numbered list, while a GUI view might show cards or a table.
 *
 * @param recipes the recipes to display; never null, may be empty
 */
void showRecipeList(List<Recipe> recipes);

/**
 * Prompts the user to create a new recipe.
 *
 * The implementation should collect all required fields from the user:
 * title, description, servings, ingredients, and category.
 * Available categories are provided so the user can choose from existing ones.
 *
 * @param availableCategories categories the user can choose from; never null
 * @return a new Recipe with all fields filled in, or null if the user cancels
 */
Recipe promptForNewRecipe(List<Category> availableCategories);
```

### Good JavaDoc Patterns

1. **Start with what the method does** (first sentence is the summary)
2. **Describe edge cases** (empty lists, null returns, cancellation)
3. **Explain constraints** ("never null", "may be empty", "at least one")
4. **Avoid implementation details** (no "uses HashMap", "prints to console")
5. **Document every parameter and return value**

### Bad JavaDoc Examples (and Fixes)

```java
// BAD: describes implementation, not contract
/**
 * Prints each recipe to System.out with a number prefix.
 */
void showRecipeList(List<Recipe> recipes);

// GOOD: describes what the method does abstractly
/**
 * Displays a list of recipes for the user to browse.
 * The display format is implementation-specific.
 *
 * @param recipes the recipes to display; never null, may be empty
 */
void showRecipeList(List<Recipe> recipes);
```

```java
// BAD: says nothing useful
/**
 * Saves a recipe.
 */
void save(Recipe recipe);

// GOOD: describes behavior and edge cases
/**
 * Persists the given recipe. If a recipe with the same ID already exists,
 * it is replaced with the new version. If the recipe is new, it is added.
 *
 * @param recipe the recipe to save; must not be null, must have a non-null ID
 */
void save(Recipe recipe);
```

## Records as Data Transfer Objects

Records carry data between layers. They should be complete, immutable, and self-documenting.

### Complete Record Definition

```java
/**
 * Represents a cooking recipe with all its properties.
 *
 * Recipes are immutable. To modify a recipe, create a new instance
 * with the desired changes using the provided methods.
 *
 * @param id            unique identifier, generated at creation
 * @param title         the recipe's display name
 * @param description   detailed preparation instructions
 * @param servings      number of servings this recipe makes
 * @param ingredientIds IDs of ingredients needed (references Ingredient records)
 * @param categoryId    ID of the category this recipe belongs to
 * @param favorite      whether the user has marked this as a favorite
 */
public record Recipe(
    String id,
    String title,
    String description,
    int servings,
    List<String> ingredientIds,
    String categoryId,
    boolean favorite
) {
    /**
     * Creates a new recipe with a generated ID and default values.
     * The recipe is not marked as favorite by default.
     */
    public static Recipe create(String title, String description, int servings,
                                List<String> ingredientIds, String categoryId) {
        return new Recipe(
            java.util.UUID.randomUUID().toString(),
            title, description, servings, List.copyOf(ingredientIds), categoryId, false
        );
    }

    /**
     * Returns a new recipe identical to this one but marked as favorite.
     */
    public Recipe markAsFavorite() {
        return new Recipe(id, title, description, servings, ingredientIds, categoryId, true);
    }

    /**
     * Returns a readable representation for display in lists and dropdowns.
     */
    @Override
    public String toString() {
        String star = favorite ? " *" : "";
        return String.format("%s (%d servings)%s", title, servings, star);
    }
}
```

### Record Design Checklist

For each record, verify:
- [ ] Has a String `id` field for unique identification
- [ ] All fields have appropriate types (not just String for everything)
- [ ] Has a `create()` factory method that generates an ID
- [ ] Has methods for common modifications (returning new instances)
- [ ] Has a meaningful `toString()` for display purposes
- [ ] References other entities by ID, not by object
- [ ] Has record-level JavaDoc with `@param` tags for all fields

## Designing for Testability

A key test of interface quality: can you write a mock implementation?

### Mock View Pattern

```java
/**
 * Mock implementation of RecipeView for testing.
 * Queues responses for prompt methods and records all displayed data.
 */
public class MockRecipeView implements RecipeView {
    // Queues for programmed responses
    private final Queue<Recipe> recipesToReturn = new LinkedList<>();
    private final Queue<Boolean> confirmationsToReturn = new LinkedList<>();

    // Records of what was displayed
    private final List<List<Recipe>> displayedRecipeLists = new ArrayList<>();
    private final List<String> successMessages = new ArrayList<>();
    private final List<String> errorMessages = new ArrayList<>();

    // Program the mock
    public void queueRecipeResponse(Recipe recipe) {
        recipesToReturn.add(recipe);
    }

    public void queueConfirmation(boolean confirm) {
        confirmationsToReturn.add(confirm);
    }

    // Verify what happened
    public List<String> getSuccessMessages() {
        return List.copyOf(successMessages);
    }

    // Interface implementation
    @Override
    public void showRecipeList(List<Recipe> recipes) {
        displayedRecipeLists.add(List.copyOf(recipes));
    }

    @Override
    public Recipe promptForNewRecipe(List<Category> categories) {
        return recipesToReturn.poll();
    }

    @Override
    public void showSuccess(String message) {
        successMessages.add(message);
    }
    // ... etc.
}
```

### Testability Checklist

For each interface method, ask:
- Can I queue a return value for this method in a mock?
- Can I verify this method was called with the right arguments?
- Does the return type allow me to control the test scenario?

If any method is hard to mock, the design might need adjustment.

## Putting It All Together

### Complete Interface Design Process

1. **List all methods** needed by the Controller
2. **Choose return types** that communicate intent
3. **Choose parameter types** that are specific and useful
4. **Write JavaDoc** that describes the contract
5. **Consider edge cases** (empty lists, missing items, cancellation)
6. **Verify testability** by sketching a mock implementation
7. **Review with a peer** --- can they understand the interface from JavaDoc alone?

### Quality Indicators

Your interface design is ready when:
- Someone could implement it from the JavaDoc alone
- You can write a mock in under 30 minutes
- Every method has a clear, single purpose
- Return types communicate whether results are optional or guaranteed
- Parameters use specific types, not generic ones

## Summary

This reading covered:
1. **Method signatures** --- return types and parameter types tell a story
2. **JavaDoc** --- describes the contract, not the implementation
3. **Records** --- complete, immutable data carriers with factory methods
4. **Testability** --- well-designed interfaces are easy to mock

The goal this week is precision. Move from "roughly right" to "exactly specified." Your future
self (and your tests in Week 13) will thank you.

---

**Next step:** Complete the pre-class exercises to specify your project's interfaces in detail.
