# Week 13 Pre-Class Reading: Testing Against Interfaces

## Introduction

You have spent weeks designing interfaces. Now comes the payoff: because your system is designed
around interfaces, you can test it without implementing a single line of production code.

This reading covers why and how to test against interfaces, how to create mock objects and
in-memory implementations, and how to organize a comprehensive test suite.

## The Core Idea: Test the Contract

When you test against an interface, you are testing the **contract** --- the promises the
interface makes. You are NOT testing:
- How data is stored (in memory? in a file? in a database?)
- How the UI looks (console? JavaFX? web?)
- How the algorithm works internally

You ARE testing:
- When I save something, can I find it again?
- When the user adds an item, does the system confirm it?
- When I search with a filter, do I get the right results?

## Mock Objects: Faking the View

A **mock object** is a fake implementation of an interface used for testing. For View interfaces,
mocks let you test the Controller without any real UI.

### How Mocks Work

```java
/**
 * Mock implementation of RecipeView for testing.
 *
 * Input methods (prompt*) return pre-queued responses.
 * Output methods (show*) record what was displayed.
 * Verification methods let tests check what happened.
 */
public class MockRecipeView implements RecipeView {

    // ===== Input queues: program what the "user" will do =====
    private final Queue<Recipe> recipesToReturn = new LinkedList<>();
    private final Queue<Optional<Recipe>> selectionsToReturn = new LinkedList<>();
    private final Queue<Boolean> confirmationsToReturn = new LinkedList<>();

    // ===== Output records: remember what was displayed =====
    private final List<List<Recipe>> displayedLists = new ArrayList<>();
    private final List<Recipe> displayedDetails = new ArrayList<>();
    private final List<String> successMessages = new ArrayList<>();
    private final List<String> errorMessages = new ArrayList<>();

    // ===== Setup methods: tell the mock what to return =====

    public void queueRecipeInput(Recipe recipe) {
        recipesToReturn.add(recipe);
    }

    public void queueSelection(Optional<Recipe> selection) {
        selectionsToReturn.add(selection);
    }

    public void queueConfirmation(boolean confirm) {
        confirmationsToReturn.add(confirm);
    }

    // ===== Verification methods: check what happened =====

    public List<String> getSuccessMessages() {
        return List.copyOf(successMessages);
    }

    public List<String> getErrorMessages() {
        return List.copyOf(errorMessages);
    }

    public List<List<Recipe>> getDisplayedLists() {
        return List.copyOf(displayedLists);
    }

    // ===== Interface implementation =====

    @Override
    public void showRecipeList(List<Recipe> recipes) {
        displayedLists.add(List.copyOf(recipes));
    }

    @Override
    public void showRecipeDetail(Recipe recipe) {
        displayedDetails.add(recipe);
    }

    @Override
    public Recipe promptForNewRecipe(List<Category> categories) {
        return recipesToReturn.poll();  // Returns null if queue is empty
    }

    @Override
    public Optional<Recipe> promptSelectRecipe(List<Recipe> recipes) {
        return selectionsToReturn.poll();
    }

    @Override
    public boolean promptConfirmDelete(Recipe recipe) {
        Boolean result = confirmationsToReturn.poll();
        return result != null ? result : false;
    }

    @Override
    public void showSuccess(String message) {
        successMessages.add(message);
    }

    @Override
    public void showError(String message) {
        errorMessages.add(message);
    }
}
```

### Using the Mock in Tests

```java
@Test
void addRecipeSavesToRepositoryAndConfirms() {
    // Arrange: program the mock
    Recipe newRecipe = Recipe.create("Test Cake", "Mix and bake", 4,
            List.of(), "cat-1");
    mockView.queueRecipeInput(newRecipe);

    // Act: run the controller method
    controller.addRecipe();

    // Assert: verify the outcome
    // Recipe was saved to repository
    Optional<Recipe> saved = recipeRepo.findById(newRecipe.id());
    assertTrue(saved.isPresent(), "Recipe should be saved to repository");
    assertEquals("Test Cake", saved.get().title());

    // Success message was shown
    assertEquals(1, mockView.getSuccessMessages().size(),
            "Should show exactly one success message");
}
```

## In-Memory Repository: Faking Persistence

An **in-memory repository** uses a HashMap instead of files or a database. It implements
the same interface, so the Controller cannot tell the difference.

### InMemoryRepository Pattern

```java
public class InMemoryRecipeRepository implements RecipeRepository {

    private final Map<String, Recipe> storage = new HashMap<>();

    @Override
    public void save(Recipe recipe) {
        storage.put(recipe.id(), recipe);
    }

    @Override
    public Optional<Recipe> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Recipe> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Recipe> findByCategory(String categoryId) {
        return storage.values().stream()
                .filter(r -> r.categoryId().equals(categoryId))
                .toList();
    }

    @Override
    public List<Recipe> findFavorites() {
        return storage.values().stream()
                .filter(Recipe::favorite)
                .toList();
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }
}
```

### Testing the Repository Itself

You can also test the InMemoryRepository directly:

```java
class InMemoryRecipeRepositoryTest {

    private InMemoryRecipeRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryRecipeRepository();
    }

    @Test
    void savedRecipeCanBeFoundById() {
        Recipe recipe = Recipe.create("Cake", "Mix and bake", 4, List.of(), "cat-1");
        repo.save(recipe);

        Optional<Recipe> found = repo.findById(recipe.id());

        assertTrue(found.isPresent());
        assertEquals("Cake", found.get().title());
    }

    @Test
    void findByIdReturnsEmptyForNonExistentId() {
        Optional<Recipe> found = repo.findById("non-existent");

        assertTrue(found.isEmpty());
    }

    @Test
    void findAllReturnsAllSavedRecipes() {
        repo.save(Recipe.create("Cake", "", 4, List.of(), "cat-1"));
        repo.save(Recipe.create("Soup", "", 2, List.of(), "cat-2"));

        List<Recipe> all = repo.findAll();

        assertEquals(2, all.size());
    }

    @Test
    void findByCategoryFiltersCorrectly() {
        repo.save(Recipe.create("Cake", "", 4, List.of(), "dessert"));
        repo.save(Recipe.create("Soup", "", 2, List.of(), "main"));
        repo.save(Recipe.create("Pie", "", 6, List.of(), "dessert"));

        List<Recipe> desserts = repo.findByCategory("dessert");

        assertEquals(2, desserts.size());
    }

    @Test
    void deleteRemovesRecipe() {
        Recipe recipe = Recipe.create("Cake", "", 4, List.of(), "cat-1");
        repo.save(recipe);

        repo.delete(recipe.id());

        assertTrue(repo.findById(recipe.id()).isEmpty());
    }
}
```

## JUnit 5 Features You Should Know

### @BeforeEach: Set Up Clean State

```java
@BeforeEach
void setUp() {
    mockView = new MockRecipeView();
    recipeRepo = new InMemoryRecipeRepository();
    categoryRepo = new InMemoryCategoryRepository();
    controller = new RecipeController(mockView, recipeRepo, categoryRepo);
}
```

Every test starts with fresh objects. No test affects another.

### @Test and @DisplayName: Document Intent

```java
@Test
@DisplayName("Adding a recipe saves it to the repository")
void addRecipeSavesToRepository() {
    // ...
}
```

The display name describes the behavior being tested, not the implementation.

### Assertions: Verify Outcomes

```java
// Check equality
assertEquals(expected, actual, "message if fails");

// Check boolean conditions
assertTrue(condition, "message if false");
assertFalse(condition, "message if true");

// Check null/presence
assertNotNull(value, "message if null");
assertTrue(optional.isPresent(), "should find the item");

// Check collections
assertEquals(3, list.size(), "should have 3 items");
assertTrue(list.contains(item), "should include the item");
assertTrue(list.isEmpty(), "should be empty");
```

### Arrange-Act-Assert Pattern

Every test follows this structure:

```java
@Test
void descriptiveTestName() {
    // Arrange: set up the test scenario
    Recipe recipe = Recipe.create("Test", "", 4, List.of(), "cat-1");
    mockView.queueRecipeInput(recipe);

    // Act: perform the action being tested
    controller.addRecipe();

    // Assert: verify the expected outcome
    assertTrue(recipeRepo.findById(recipe.id()).isPresent());
}
```

## Test Categories for Your Project

### 1. Model Tests (Record Behavior)
Test your records' factory methods, modification methods, and toString:

```java
@Test
void createGeneratesUniqueId() {
    Recipe r1 = Recipe.create("A", "", 1, List.of(), "c1");
    Recipe r2 = Recipe.create("B", "", 1, List.of(), "c1");
    assertNotEquals(r1.id(), r2.id());
}

@Test
void markAsFavoriteReturnsNewInstance() {
    Recipe original = Recipe.create("Cake", "", 4, List.of(), "c1");
    Recipe favorite = original.markAsFavorite();

    assertFalse(original.favorite());
    assertTrue(favorite.favorite());
    assertEquals(original.id(), favorite.id()); // Same ID
}
```

### 2. Repository Tests (Data Operations)
Test CRUD operations and domain queries:

```java
@Test
void saveAndFindById() { ... }

@Test
void findByIdNotFound() { ... }

@Test
void findAllEmpty() { ... }

@Test
void findAllWithItems() { ... }

@Test
void findByCategory() { ... }

@Test
void deleteExisting() { ... }

@Test
void deleteNonExistent() { ... }

@Test
void updateExisting() { ... }
```

### 3. Controller Tests (Integration)
Test that the Controller coordinates View and Repository correctly:

```java
@Test
void addRecipeSavesAndConfirms() { ... }

@Test
void addRecipeCancelledDoesNotSave() { ... }

@Test
void listRecipesShowsAllFromRepository() { ... }

@Test
void deleteRecipeConfirmedRemovesFromRepository() { ... }

@Test
void deleteRecipeCancelledDoesNotRemove() { ... }

@Test
void searchByCategoryShowsFilteredResults() { ... }
```

## How Many Tests Do You Need?

A good rule of thumb for your exam project:
- **3-5 model tests** (factory methods, modifications, toString)
- **5-8 repository tests** (CRUD + domain queries)
- **5-10 controller tests** (one per major user operation + error paths)

Total: **13-23 tests** for a well-tested project.

## What Tests Reveal About Your Design

If you encounter these situations, take action:

| Situation | What It Means | Action |
|-----------|--------------|--------|
| Cannot create a mock easily | Interface has too many methods or complex return types | Simplify the interface |
| Test needs many queue calls | Operation is too complex | Break into smaller steps |
| Cannot test an operation | Missing interface method | Add the method |
| Test is hard to read | Workflow is convoluted | Simplify the workflow |
| Same setup in every test | Common pattern needed | Extract to @BeforeEach |

## Summary

Testing against interfaces is the bridge between design and implementation:

1. **Mock objects** fake the View for testing without a UI
2. **InMemory repositories** fake persistence for testing without files
3. **JUnit 5** provides the framework for writing and running tests
4. **Arrange-Act-Assert** keeps tests clear and focused
5. **Test categories** ensure coverage: Model, Repository, Controller

The tests you write this week become the specification for your 2-week implementation.
Every test is a target: implement the code, make the test pass, move on.

---

**Next step:** Complete the pre-class exercises to plan your test strategy.
