# Week 11 Hints: Architecture Document

## Getting Started

### Hint 1: Use your pitch as the foundation
Your pre-class pitch has the project name, entities, interfaces, and patterns. The architecture
document expands on each of these with full detail.

### Hint 2: Start with the interfaces map
The interfaces map is the most important part. For each interface, list every method with its
full signature:

```java
put("RecipeView",
    "void showRecipeList(List<Recipe> recipes)\n" +
    "void showRecipeDetail(Recipe recipe)\n" +
    "Recipe promptForNewRecipe(List<Category> categories)\n" +
    "Optional<Recipe> promptSelectRecipe(List<Recipe> recipes)\n" +
    "boolean promptConfirmDelete(Recipe recipe)\n" +
    "void showSuccess(String message)\n" +
    "void showError(String message)");
```

### Hint 3: Think about what the Controller needs
Your Controller calls methods on the View and Repository interfaces. Ask yourself:
- What does the Controller need to show the user? Those become View methods.
- What data does the Controller need to fetch or save? Those become Repository methods.

## Defining Interfaces

### Hint 4: View interface methods fall into three categories
1. **Display methods:** `void showXxx(DataType data)` --- show information to the user
2. **Input methods:** `DataType promptForXxx()` --- get information from the user
3. **Feedback methods:** `void showSuccess(String)`, `void showError(String)` --- confirmations

### Hint 5: Repository interface methods follow a standard pattern
Every Repository should have basic CRUD:
```
void save(Entity entity)
Optional<Entity> findById(String id)
List<Entity> findAll()
void delete(String id)
```

Then add domain-specific queries:
```
List<Recipe> findByCategory(String categoryId)
List<Recipe> searchByTitle(String keyword)
List<Recipe> findFavorites()
```

### Hint 6: Consider a generic Repository base
Both example projects use a pattern like:
```
Repository<T, ID>           (generic base)
    └── RecipeRepository    (adds domain methods)
        └── InMemoryRecipeRepository (implementation)
```

You can follow this same hierarchy.

## Defining Records

### Hint 7: Every record needs an ID field
```java
put("Recipe",
    "String id - unique identifier (UUID)\n" +
    "String title - recipe name\n" +
    ...);
```

The ID is how entities reference each other and how the Repository looks them up.

### Hint 8: Reference other entities by ID
Instead of:
```
Recipe recipe - the recipe this belongs to (BAD: object reference)
```

Use:
```
String recipeId - reference to the Recipe this belongs to (GOOD: ID reference)
```

### Hint 9: Consider what fields you actually need
For each field, ask:
- Does the user need to see this? (If not, maybe it is not needed)
- Does the system need this for business logic? (If not, skip it)
- Is this a core property or a nice-to-have? (Start with core, add others later)

## Architecture Layers

### Hint 10: Copy the example project structure
A good starting template:
```
VIEW LAYER:
  YourView (interface) -> ConsoleYourView, MockYourView

CONTROLLER LAYER:
  YourController - coordinates View and Repository

MODEL LAYER:
  Entity1 (record), Entity2 (record), Enum1 (enum)

REPOSITORY LAYER:
  Entity1Repository (interface) -> InMemoryEntity1Repository
  Entity2Repository (interface) -> InMemoryEntity2Repository
```

### Hint 11: The composition root ties everything together
Your architecture should show the composition root (main class) at the top:
```
COMPOSITION ROOT:
  YourApp.main() - creates concrete implementations and wires them together
```

This is where you create `new InMemoryRepository()` and `new ConsoleView()` and pass them to
the Controller.

## Formatted Document

### Hint 12: Structure the output clearly
Use sections with headers:
```java
public String getFormattedDocument() {
    var sb = new StringBuilder();
    sb.append("=== ").append(projectName).append(" ===\n");
    sb.append(projectSummary).append("\n\n");

    sb.append("--- INTERFACES ---\n");
    for (var entry : interfaces.entrySet()) {
        sb.append("\n").append(entry.getKey()).append(":\n");
        sb.append(entry.getValue()).append("\n");
    }

    sb.append("\n--- RECORDS ---\n");
    for (var entry : records.entrySet()) {
        sb.append("\n").append(entry.getKey()).append(":\n");
        sb.append(entry.getValue()).append("\n");
    }

    sb.append("\n--- ARCHITECTURE ---\n");
    sb.append(architectureLayers).append("\n");

    return sb.toString();
}
```

## Common Mistakes

### Mistake 1: Interfaces that are too vague
Bad: `void show(Object data)`
Good: `void showRecipeList(List<Recipe> recipes)`

Specific types make it clear what each method does and enable compile-time checking.

### Mistake 2: Missing the Mock View
Remember to plan for a MockView implementation. It is essential for testing your Controller.

### Mistake 3: Forgetting entity relationships
If Recipe has ingredients, you need both a way to store that relationship (ingredientIds list)
and a way to query it (findByRecipeId in IngredientRepository).

### Mistake 4: Controller doing too much
The Controller coordinates --- it should not contain complex algorithms. If you have complex
logic, consider a Strategy interface.

## After Completing

Your architecture document will be refined further in:
- **Week 12:** Deep dive into method signatures, JavaDoc, and parameter types
- **Week 13:** Test planning based on your interfaces
- **Week 14:** Final review and project setup

This is a living document. It is okay to change things as your understanding deepens.
