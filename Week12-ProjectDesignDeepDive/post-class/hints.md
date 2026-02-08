# Week 12 Hints: Design Document

## Writing Good JavaDoc

### Hint 1: Follow the three-part structure
For each interface method, document:
1. **What it does** (first sentence)
2. **How implementations should behave** (additional details)
3. **Parameters, return values, edge cases** (@param, @return, special behavior)

### Hint 2: Describe contracts, not implementations
Bad: "Prints recipes to the console using System.out.println"
Good: "Displays a list of recipes for the user to browse. The display format is implementation-specific."

The key test: would this JavaDoc still be accurate for a JavaFX implementation?

### Hint 3: Document null and empty behavior
For every parameter, state whether null is allowed:
- "never null" --- callers must check before calling
- "may be null" --- implementation must handle null

For every List parameter, state what empty means:
- "may be empty" --- implementation shows a "no items" message
- "must have at least one element" --- caller ensures non-empty

### Hint 4: Use @param and @return consistently
```
Method: Optional<Recipe> findById(String id)
@param id - the unique identifier to search for; must not be null or blank
@return the recipe with the given ID, or Optional.empty() if not found
```

## Writing Workflow Pseudo-Code

### Hint 5: Follow a consistent format
```
OPERATION: Add New Recipe
1. [Controller] addRecipe() is called
2. [Controller] categories = categoryRepo.findAll()
3. [Controller] recipe = view.promptForNewRecipe(categories)
4. [Controller] if recipe is null, return (user cancelled)
5. [Controller] validate recipe fields
6. [Controller] recipeRepo.save(recipe)
7. [Controller] view.showSuccess("Recipe added successfully")
ERROR: validation fails at step 5
5a. [Controller] view.showError("Title cannot be blank")
5b. Go to step 3
```

### Hint 6: Include the error path
Every workflow should have at least one error path. Ask:
- What if the user cancels?
- What if validation fails?
- What if the item is not found?

### Hint 7: Workflows become your Controller methods
Each workflow maps directly to a method in your Controller:
- "Add New Recipe" -> `addRecipe()`
- "Search by Category" -> `searchByCategory()`
- "Delete Recipe" -> `deleteRecipe()`

## Sketching Mock Implementations

### Hint 8: Mocks need queues for input and lists for output
```
MockRecipeView needs:
  INPUT (queued responses):
    Queue<Recipe> recipesToReturn        -> for promptForNewRecipe()
    Queue<Optional<Recipe>> selectionsToReturn -> for promptSelectRecipe()
    Queue<Boolean> confirmationsToReturn -> for promptConfirmDelete()

  OUTPUT (recorded calls):
    List<List<Recipe>> displayedLists  -> from showRecipeList()
    List<Recipe> displayedDetails      -> from showRecipeDetail()
    List<String> successMessages       -> from showSuccess()
    List<String> errorMessages         -> from showError()
```

### Hint 9: InMemoryRepository is simpler
```
InMemoryRecipeRepository needs:
  Map<String, Recipe> storage = new HashMap<>();

  save(recipe)    -> storage.put(recipe.id(), recipe)
  findById(id)    -> Optional.ofNullable(storage.get(id))
  findAll()       -> new ArrayList<>(storage.values())
  delete(id)      -> storage.remove(id)
  findByCategory(catId) -> storage.values().stream()
                            .filter(r -> r.categoryId().equals(catId))
                            .toList()
```

## Documenting Edge Cases

### Hint 10: Think about boundaries
For each interface method, consider:
- What if the list is empty?
- What if the ID does not exist?
- What if the user provides blank input?
- What if the user cancels?
- What if there are no categories to choose from?

### Hint 11: Decide on return types for edge cases
There are two approaches:
- **Optional:** `findById` returns `Optional.empty()` if not found
- **Exception:** `findById` throws `EntityNotFoundException` if not found

For this course, **prefer Optional over exceptions** for expected cases (item not found).
Reserve exceptions for programming errors (null ID passed).

### Hint 12: Document the decision
For each edge case, document:
1. What triggers it
2. What the interface method returns or does
3. What the Controller should do in response

## Common Mistakes

### Mistake 1: JavaDoc that just repeats the method name
Bad: "Saves a recipe." (for the save method)
Good: "Persists the given recipe. If a recipe with the same ID exists, it is replaced."

### Mistake 2: Missing workflow steps
Do not skip the validation step or the feedback step. Users need to know what happened.

### Mistake 3: Mock sketch that does not match the interface
Every interface method should appear in the mock sketch. If a method is hard to mock,
the interface design might need adjustment.

### Mistake 4: Ignoring the cancel/abort path
What if the user presses "Cancel" during input? Your mock needs `null` returns to test this.

## After Completing

Your design document will be used in:
- **Week 13:** Writing actual JUnit tests against your interfaces
- **Week 14:** Setting up the Maven project structure
- **Implementation:** The workflows become your Controller methods

Keep this document updated as your understanding evolves.
