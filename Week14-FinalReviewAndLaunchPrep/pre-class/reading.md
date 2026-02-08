# Week 14 Pre-Class Reading: From Design to Implementation

## Introduction

You have a complete design: interfaces, records, tests, and an architecture diagram. The
question now is: how do you turn that design into working code? This reading covers the
practical steps of implementation: project setup, implementation order, git workflow,
and strategies for staying on track.

## Setting Up Your Maven Project

### Creating the Project

You can create a Maven project from IntelliJ or from the command line:

**From IntelliJ:**
1. File -> New -> Project
2. Select "Maven Archetype" (or just "New Project" with Maven build system)
3. Set GroupId: `dk.viprogram`
4. Set ArtifactId: `your-project-name`
5. Set JDK: 21

**From command line:**
```bash
mvn archetype:generate \
  -DgroupId=dk.viprogram \
  -DartifactId=your-project-name \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false
```

### The pom.xml

Your `pom.xml` should include Java 21 and JUnit 5:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>dk.viprogram</groupId>
    <artifactId>your-project-name</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
            </plugin>
        </plugins>
    </build>
</project>
```

If you plan a JavaFX view, you will also need the JavaFX dependencies and plugin.

### Package Structure

Create packages matching your architecture layers:

```
src/main/java/dk/viprogram/yourproject/
    ├── model/           # Records and enums
    ├── repository/      # Repository interfaces
    │   └── inmemory/    # InMemory implementations
    ├── view/            # View interface
    │   ├── console/     # Console implementation
    │   └── mock/        # Mock for testing
    ├── controller/      # Controller
    └── YourApp.java     # Composition root

src/test/java/dk/viprogram/yourproject/
    ├── model/           # Record tests
    ├── repository/      # Repository tests
    └── controller/      # Controller integration tests
```

## Implementation Order: Start with Leaf Nodes

The key principle: **implement things that have no dependencies first.**

### Phase 1: Model Layer (No Dependencies)

Records and enums depend on nothing. They are pure data:

```java
// This can be written first - no dependencies
public record Recipe(
    String id,
    String title,
    String description,
    int servings,
    List<String> ingredientIds,
    String categoryId,
    boolean favorite
) {
    public static Recipe create(String title, String description,
                                int servings, List<String> ingredientIds,
                                String categoryId) {
        return new Recipe(
            java.util.UUID.randomUUID().toString(),
            title, description, servings,
            List.copyOf(ingredientIds), categoryId, false
        );
    }

    public Recipe markAsFavorite() {
        return new Recipe(id, title, description, servings,
                          ingredientIds, categoryId, true);
    }
}
```

**After Phase 1:** Run model tests. They should pass.

### Phase 2: Repository Layer (Depends on Model)

Interfaces first, then InMemory implementations:

```java
// Interface - just the contract
public interface RecipeRepository {
    void save(Recipe recipe);
    Optional<Recipe> findById(String id);
    List<Recipe> findAll();
    void delete(String id);
    List<Recipe> findByCategory(String categoryId);
}

// InMemory implementation - uses HashMap
public class InMemoryRecipeRepository implements RecipeRepository {
    private final Map<String, Recipe> storage = new HashMap<>();

    @Override
    public void save(Recipe recipe) {
        storage.put(recipe.id(), recipe);
    }
    // ... other methods
}
```

**After Phase 2:** Run repository tests. They should pass.

### Phase 3: Mock View and Controller (Depends on Model + Repository)

```java
// MockView - for testing
public class MockRecipeView implements RecipeView {
    // ... queues and lists
}

// Controller - the coordinator
public class RecipeController {
    private final RecipeView view;
    private final RecipeRepository recipeRepo;

    public RecipeController(RecipeView view, RecipeRepository recipeRepo) {
        this.view = view;
        this.recipeRepo = recipeRepo;
    }

    public void addRecipe() {
        Recipe recipe = view.promptForNewRecipe(/* ... */);
        if (recipe != null) {
            recipeRepo.save(recipe);
            view.showSuccess("Recipe added successfully");
        }
    }
    // ... other methods
}
```

**After Phase 3:** Run controller tests. They should pass.

### Phase 4: Console View (Depends on Model + Java standard library)

```java
public class ConsoleRecipeView implements RecipeView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showRecipeList(List<Recipe> recipes) {
        if (recipes.isEmpty()) {
            System.out.println("No recipes found.");
            return;
        }
        for (int i = 0; i < recipes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, recipes.get(i));
        }
    }
    // ... other methods
}
```

### Phase 5: Composition Root (Wires Everything Together)

```java
public class RecipeApp {
    public static void main(String[] args) {
        var recipeRepo = new InMemoryRecipeRepository();
        var categoryRepo = new InMemoryCategoryRepository();
        seedSampleData(recipeRepo, categoryRepo);

        var view = new ConsoleRecipeView();
        var controller = new RecipeController(view, recipeRepo, categoryRepo);
        controller.run();
    }

    private static void seedSampleData(RecipeRepository recipeRepo,
                                       CategoryRepository categoryRepo) {
        // Add sample data for demonstration
    }
}
```

**After Phase 5:** The application runs!

## Git Workflow for Your Project

### Initial Setup

```bash
# Initialize the repository
git init

# Create .gitignore
echo "target/
*.class
*.jar
.idea/
*.iml
.DS_Store" > .gitignore

# First commit
git add .
git commit -m "Initial project setup with Maven and package structure"
```

### Commit After Each Phase

```bash
# After implementing records
git add .
git commit -m "Add model records: Recipe, Category, Ingredient"

# After implementing repository
git add .
git commit -m "Add Repository interfaces and InMemory implementations"

# After implementing controller
git add .
git commit -m "Add Controller with mock view and tests"

# After implementing console view
git add .
git commit -m "Add ConsoleView implementation"
```

### Good Commit Messages

- Start with a verb: "Add", "Fix", "Update", "Remove"
- Be specific: "Add Recipe record with factory method" not "Add stuff"
- Reference the layer: "Add InMemoryRecipeRepository" not "Add repository"

### When to Commit

- After each phase is complete and tests pass
- Before making risky changes
- At the end of each coding session
- When you reach a milestone (e.g., "first operation works end-to-end")

## Common Implementation Pitfalls

### Pitfall 1: Starting with the View
The View is the last thing to implement. Start with records and work up.

### Pitfall 2: Redesigning During Implementation
Resist the urge to redesign your interfaces. Small adjustments are fine (adding a method,
changing a return type). Complete restructuring will cost you days.

### Pitfall 3: Not Running Tests
Run `mvn test` after every change. If tests break, fix them immediately. Do not accumulate
broken tests.

### Pitfall 4: Over-Polishing One Feature
Get all features working at a basic level before polishing any single feature.
An application with 5 working features beats one with 1 polished feature and 4 broken ones.

### Pitfall 5: Not Seeding Sample Data
When you run your application, it starts with empty data. Add a `seedSampleData()` method
in your composition root so the app has data to work with immediately.

### Pitfall 6: Ignoring toString
Records need good `toString()` methods for console display and JavaFX dropdowns.
Implement these early:
```java
@Override
public String toString() {
    return String.format("%s (%d servings)", title, servings);
}
```

## Running and Testing Your Project

### Build and Test

```bash
# Compile and run all tests
mvn clean test

# Run only a specific test class
mvn test -Dtest=RecipeControllerTest

# Compile without running tests
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="dk.viprogram.yourproject.YourApp"
```

### When Tests Fail

1. Read the failure message carefully
2. Check if it is a compilation error (missing class) or a logic error (wrong result)
3. If compilation: you are probably missing an import or a class
4. If logic: trace the flow using your workflow diagrams

## Staying on Track

### Daily Checklist
- [ ] What did I implement yesterday?
- [ ] What will I implement today?
- [ ] Are all tests still passing?
- [ ] Have I committed my changes?

### Mid-Implementation Review (End of Week 1)
- [ ] All records implemented and tested
- [ ] All InMemory repositories implemented and tested
- [ ] Controller implemented for at least 3 operations
- [ ] Controller tests passing
- [ ] ConsoleView has at least basic functionality

### Pre-Submission Review (End of Week 2)
- [ ] All planned operations work end-to-end
- [ ] All tests pass
- [ ] Sample data is seeded
- [ ] Application can be demonstrated
- [ ] Code is clean and commented

## Summary

The path from design to implementation is straightforward if you follow the order:

1. **Set up** Maven project and package structure
2. **Copy** interfaces and records from your design
3. **Implement** bottom-up: Model -> Repository -> Controller -> View
4. **Run tests** after each phase
5. **Commit** frequently with descriptive messages

Your design documents are your implementation guide. Trust them. Follow them. Your 4 weeks
of design work will pay off as implementation becomes a matter of filling in the blanks.

---

**Next step:** Complete the pre-class exercises to create your implementation roadmap.
