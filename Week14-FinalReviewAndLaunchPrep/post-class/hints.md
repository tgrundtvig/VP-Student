# Week 14 Hints: Project Launch

## Setting Up the Maven Project

### Hint 1: IntelliJ makes it easy
In IntelliJ: File -> New -> Project -> Maven Archetype -> maven-archetype-quickstart.
Or just use "New Project" with Build System set to Maven.

### Hint 2: Delete the generated files
Maven generates placeholder files (App.java, AppTest.java). Delete them and create your own
package structure.

### Hint 3: Copy the pom.xml from the reading
The pre-class reading has a complete pom.xml template. Copy it and adjust the artifactId.

## Creating the Package Structure

### Hint 4: Create packages from IntelliJ
Right-click on src/main/java -> New -> Package -> type the full package name.
IntelliJ creates all intermediate directories.

### Hint 5: Follow the example projects
The example projects have this structure:
```
model/       -> records and enums
repository/  -> interfaces
repository/inmemory/ -> InMemory implementations
view/        -> View interface
view/console/ -> ConsoleView
view/mock/   -> MockView
controller/  -> Controller
```

### Hint 6: Create test packages to mirror main
Your test directory should mirror your main directory:
```
src/test/java/dk/viprogram/yourproject/model/
src/test/java/dk/viprogram/yourproject/repository/
src/test/java/dk/viprogram/yourproject/controller/
```

## Copying Interfaces and Records

### Hint 7: Translate from your design documents
Your Week 12 design document has the method signatures. Convert them to actual Java code:

Design document entry:
```
"void showRecipeList(List<Recipe> recipes) - displays all recipes"
```

Becomes actual Java:
```java
/**
 * Displays a list of recipes for the user to browse.
 * If the list is empty, shows a "no recipes found" message.
 *
 * @param recipes the recipes to display; never null, may be empty
 */
void showRecipeList(List<Recipe> recipes);
```

### Hint 8: Records need imports
Records with List fields need `import java.util.List;`.
Records with UUID in factory methods need `import java.util.UUID;`.

### Hint 9: Start minimal with the mock
Your MockView can start with the minimum needed for tests to compile. You can add more
queue/verification methods later as you write more tests.

## Getting the Project to Compile

### Hint 10: Compile frequently
After creating each file, run `mvn clean compile` to catch errors early.

### Hint 11: Some tests will not compile yet
Controller tests depend on the Controller class, which does not exist yet.
You have two options:
- Comment out controller tests for now (uncomment when you implement Controller)
- Create an empty Controller class with TODO methods that throw UnsupportedOperationException

### Hint 12: Fix import errors first
The most common compilation error is a missing import. IntelliJ's "Optimize Imports" feature
(Ctrl+Alt+O or Cmd+Alt+O) can help.

## Git Setup

### Hint 13: Create .gitignore first
Before your first commit, create a .gitignore:
```
target/
*.class
*.jar
.idea/
*.iml
.DS_Store
```

### Hint 14: Make your first commit small
Just the project structure, pom.xml, and interfaces. Then commit records, then tests,
then mocks. Small commits make it easy to go back.

## Starting Implementation

### Hint 15: Records are the easiest starting point
Records have no dependencies and are straightforward to implement:
```java
public record Recipe(
    String id,
    String title,
    // ... fields
) {
    public static Recipe create(String title, ...) {
        return new Recipe(UUID.randomUUID().toString(), title, ...);
    }
}
```

After implementing records, run model tests. If they pass, commit and move on.

### Hint 16: InMemoryRepository is next
It depends only on records and uses standard Java (HashMap, stream):
```java
public class InMemoryRecipeRepository implements RecipeRepository {
    private final Map<String, Recipe> storage = new HashMap<>();
    // ... implement methods
}
```

After implementing, run repository tests. If they pass, commit and move on.

### Hint 17: The Controller is the big one
The Controller is where your workflow diagrams from Week 12 become real code.
Each workflow diagram becomes a method:
```java
public void addRecipe() {
    // Follow your workflow step by step
    List<Category> categories = categoryRepo.findAll();
    Recipe recipe = view.promptForNewRecipe(categories);
    if (recipe != null) {
        recipeRepo.save(recipe);
        view.showSuccess("Recipe added successfully");
    }
}
```

## Common Issues

### Issue: "My interface uses a type that does not exist yet"
Create the records first. Interfaces reference records, so records must exist for
interfaces to compile.

### Issue: "My test uses a class that does not exist yet"
Create stub implementations or comment out those specific tests. As you implement
each class, uncomment and run its tests.

### Issue: "I keep changing my mind about the design"
Small adjustments are fine (adding a field, changing a method name). Major redesigns
will cost too much time. Trust your 4 weeks of design work.

### Issue: "Everything is interconnected"
This is why you start with leaf nodes (records, which depend on nothing).
Then move to things that depend only on records (repositories).
Then things that depend on repositories and records (controller).
Then things that depend on everything (view, main class).

## After Setup

You are now in implementation mode. Refer to:
- **Week 11** for architecture and layer structure
- **Week 12** for interface specifications and JavaDoc
- **Week 13** for test code and mock implementations
- **Week 14 pre-class** for your implementation roadmap

Good luck! You have done the hard part (design). Implementation is the reward.
