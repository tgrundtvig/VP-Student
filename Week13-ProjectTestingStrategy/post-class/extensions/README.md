# Week 13 Extensions: Going Deeper

## For Students Who Want More

These extensions are for students who have completed their test suite and want to strengthen
their testing skills further.

## Extension 1: Property-Based Testing Concepts

**Goal:** Think beyond example-based tests to property-based testing.

Property-based tests verify that a **property always holds**, regardless of input.

**Examples for a Repository:**
- "For any entity saved, findById always returns it"
- "findAll().size() equals the number of unique entities saved"
- "After delete, findById returns empty"
- "save is idempotent: saving twice yields same result as saving once"

**Steps:**
1. List 3-5 properties that should always hold for your Repository
2. List 2-3 properties for your records (e.g., "create always generates non-null ID")
3. For each property, write a test that checks it with multiple inputs

**Example test:**
```java
@Test
void saveIsIdempotent() {
    Recipe recipe = Recipe.create("Test", "", 4, List.of(), "c1");
    repo.save(recipe);
    repo.save(recipe); // Save again

    assertEquals(1, repo.findAll().size(),
        "Saving the same entity twice should not create duplicates");
}
```

**Why this helps:** Property-based thinking catches bugs that specific examples miss.

## Extension 2: Edge Case Analysis

**Goal:** Systematically identify and test all edge cases for your interfaces.

**For each interface method, consider:**

| Edge Case Category | Examples |
|-------------------|----------|
| Empty input | Empty list, empty string, zero |
| Null input | Null parameter, null field |
| Boundary values | Max int, very long strings |
| Duplicate operations | Save twice, delete twice |
| Order dependency | Does operation order matter? |
| Concurrent access | Two saves at the same time |

**Steps:**
1. Create a table of edge cases for each interface method
2. Write a test for each edge case
3. Document the expected behavior

**Why this helps:** Edge cases are where most bugs hide. Thinking about them now prevents
implementation surprises.

## Extension 3: Test Coverage Checklist

**Goal:** Create a coverage checklist mapping every interface method to its tests.

**Format:**
```
RecipeView interface:
  showRecipeList(List<Recipe>)
    [x] Called when controller lists recipes
    [x] Called with empty list when no recipes exist
    [ ] Called with filtered list after search (not yet tested)

  promptForNewRecipe(List<Category>)
    [x] Returns recipe in happy path (addRecipe test)
    [x] Returns null when user cancels (cancel test)
    [ ] Called with empty category list (not yet tested)

  showSuccess(String)
    [x] Called after successful add
    [x] Called after successful delete
    [ ] Message content verified (not yet tested)
```

**Steps:**
1. List every interface method
2. For each method, list every test that exercises it
3. Identify gaps (methods with no tests or only happy-path tests)
4. Write tests to fill the gaps

**Why this helps:** A coverage checklist ensures no method goes untested.

## Extension 4: Test Data Builders

**Goal:** Create helper methods that make test data creation clean and readable.

**Example:**
```java
// Instead of this in every test:
Recipe recipe = new Recipe(
    UUID.randomUUID().toString(),
    "Test Recipe",
    "A test recipe for testing",
    4,
    List.of("i1", "i2"),
    "cat-1",
    false
);

// Create a builder helper:
public class TestDataFactory {
    public static Recipe aRecipe() {
        return Recipe.create("Test Recipe", "A test recipe", 4, List.of(), "cat-1");
    }

    public static Recipe aRecipeInCategory(String categoryId) {
        return Recipe.create("Test Recipe", "", 4, List.of(), categoryId);
    }

    public static Recipe aFavoriteRecipe() {
        return Recipe.create("Favorite", "", 4, List.of(), "cat-1").markAsFavorite();
    }

    public static Category aCategory(String name) {
        return Category.create(name, "#000000");
    }
}
```

**Why this helps:** Test data factories reduce duplication and make tests more readable.
When your entity structure changes, you update one place instead of every test.

## Extension 5: Integration Test Scenarios

**Goal:** Write end-to-end test scenarios that cover complete user workflows.

**Example scenario: "User adds a recipe, then finds it by category"**
```java
@Test
void addRecipeThenFindByCategory() {
    // Setup: create a category
    Category dessert = Category.create("Desserts", "#FF0000");
    categoryRepo.save(dessert);

    // Step 1: User adds a recipe in the "Desserts" category
    Recipe cake = Recipe.create("Cake", "Delicious", 8, List.of(), dessert.id());
    mockView.queueRecipeInput(cake);
    controller.addRecipe();

    // Step 2: User browses by category
    mockView.queueCategorySelection(Optional.of(dessert));
    controller.searchByCategory();

    // Verify: the recipe appears in the category search results
    List<List<Recipe>> displayed = mockView.getDisplayedLists();
    assertEquals(1, displayed.get(displayed.size() - 1).size());
    assertEquals("Cake", displayed.get(displayed.size() - 1).get(0).title());
}
```

**Why this helps:** Integration scenarios verify that multiple operations work together
correctly, catching issues that unit tests miss.

---

## Submission

Extensions are optional and not graded. However, completing them will give you significantly
more confidence during the 2-week implementation period. Students who write thorough tests
consistently finish their projects faster.
