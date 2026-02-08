# Week 12 Extensions: Going Deeper

## For Students Who Want More

These extensions are for students who have completed their design document and want to
strengthen their design further.

## Extension 1: Create UML-Like Diagrams

**Goal:** Visualize your system's structure using text-based class diagrams.

**Steps:**
1. Draw an interface diagram showing all interfaces, their methods, and relationships
2. Draw a record diagram showing all records, their fields, and references
3. Draw a dependency diagram showing which classes depend on which interfaces

**Example (text-based UML):**
```
┌─────────────────────────────────┐
│        <<interface>>            │
│          RecipeView             │
├─────────────────────────────────┤
│ + showRecipeList(List<Recipe>)  │
│ + showRecipeDetail(Recipe)      │
│ + promptForNewRecipe(List<Cat>) │
│ + promptSelectRecipe(List<Rec>) │
│ + showSuccess(String)           │
│ + showError(String)             │
└──────────────┬──────────────────┘
               │ implements
    ┌──────────┼──────────────┐
    │          │              │
┌───▼──┐  ┌───▼──┐    ┌──────▼──┐
│Console│  │JavaFX│    │  Mock   │
│View   │  │View  │    │  View   │
└───────┘  └──────┘    └─────────┘
```

**Why this helps:** Visual diagrams expose structural issues that text descriptions hide.

## Extension 2: Document All Preconditions and Postconditions

**Goal:** For each interface method, define formal preconditions and postconditions.

**Precondition:** What must be true BEFORE the method is called.
**Postcondition:** What is guaranteed to be true AFTER the method returns.

**Example:**
```
Method: void save(Recipe recipe)
Preconditions:
  - recipe is not null
  - recipe.id() is not null or blank
  - recipe.title() is not null or blank
Postconditions:
  - findById(recipe.id()) returns Optional containing the saved recipe
  - count() is at least 1
  - findAll() includes the saved recipe
```

**Why this helps:** Preconditions and postconditions become assertions in your tests. They make
the contract unambiguous.

## Extension 3: Design a Complete Controller Class

**Goal:** Sketch the Controller class with all its fields and methods.

**Steps:**
1. List all interfaces the Controller depends on (constructor parameters)
2. List all public methods (one per user operation)
3. For each method, reference the workflow from your design document

**Example:**
```
RecipeController:
  Fields:
    - RecipeView view
    - RecipeRepository recipeRepo
    - CategoryRepository categoryRepo

  Constructor:
    RecipeController(RecipeView view, RecipeRepository recipeRepo,
                     CategoryRepository categoryRepo)

  Methods:
    - run(): void - main loop showing menu and dispatching
    - addRecipe(): void - workflow "Add New Recipe"
    - listRecipes(): void - workflow "List All Recipes"
    - searchByCategory(): void - workflow "Search by Category"
    - deleteRecipe(): void - workflow "Delete Recipe"
    - toggleFavorite(): void - workflow "Toggle Favorite"
```

**Why this helps:** The Controller is the glue between your interfaces. Designing it now ensures
your interfaces are complete.

## Extension 4: Write a Composition Root

**Goal:** Write the main() method that wires everything together.

**Example:**
```java
public class RecipeApp {
    public static void main(String[] args) {
        // Create repositories
        var recipeRepo = new InMemoryRecipeRepository();
        var categoryRepo = new InMemoryCategoryRepository();

        // Seed sample data
        seedSampleData(recipeRepo, categoryRepo);

        // Create view (swap this line for different UI)
        var view = new ConsoleRecipeView();

        // Wire controller
        var controller = new RecipeController(view, recipeRepo, categoryRepo);

        // Start
        controller.run();
    }
}
```

**Why this helps:** The composition root proves your architecture works. If you cannot write it,
something is missing in your design.

## Extension 5: Enumerate All Test Scenarios

**Goal:** List every test you will write in Week 13, grouped by interface.

**Example:**
```
RecipeRepository tests:
  - save new recipe -> findById returns it
  - save existing recipe -> findById returns updated version
  - findById with non-existent ID -> returns empty Optional
  - findAll with no recipes -> returns empty list
  - findAll with 3 recipes -> returns all 3
  - findByCategory -> returns only matching recipes
  - delete -> findById returns empty after delete
  - delete non-existent ID -> no error

Controller tests (with MockView + InMemoryRepo):
  - addRecipe -> recipe appears in repository
  - addRecipe with cancel -> repository unchanged
  - listRecipes -> view.showRecipeList called with all recipes
  - deleteRecipe -> recipe removed from repository
  - deleteRecipe with cancel -> repository unchanged
```

**Why this helps:** Enumerating tests in advance is a preview of Week 13. It also reveals
whether your interfaces have enough methods to support all the tests.

---

## Submission

Extensions are optional and not graded. However, completing Extension 3 (Controller) and
Extension 5 (Test Scenarios) will give you a significant head start on Weeks 13-14.
