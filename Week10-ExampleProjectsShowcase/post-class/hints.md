# Week 10 Hints: Project Proposal

## Choosing a Project

### Hint 1: Start with data you care about
Think about data you interact with regularly:
- Music playlists? → Media organizer
- Grocery lists? → Shopping manager
- Exercise logs? → Fitness tracker

Projects about things you understand deeply are easier to design well.

### Hint 2: Two to four entities is the sweet spot
- 1 entity = too simple, not enough to demonstrate patterns
- 2-4 entities = demonstrates relationships, manageable scope
- 5+ entities = likely too complex for 2 weeks

### Hint 3: Think about what makes it interesting
Beyond basic CRUD, what's special about your project?
- Unique queries? (e.g., "find recipes I can make with these ingredients")
- Business rules? (e.g., "can't book overlapping time slots")
- Different views of data? (e.g., "show expenses by category/month/year")

## Defining Entities

### Hint 4: Use the example projects as templates
Look at how Task Manager defines Task:
```java
public record Task(
    String id,
    String title,
    String description,
    Priority priority,
    String categoryId,  // References another entity by ID
    LocalDate dueDate,
    boolean completed
) {
    // Custom toString() for readable dropdown display
    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return String.format("%s %s [%s]", status, title, priority);
    }
}
```

Your entities should follow the same pattern. Note the custom `toString()` - this is important for JavaFX dropdowns!

### Hint 5: Link entities by ID, not by object
Instead of:
```java
public record Order(
    String id,
    Customer customer  // Object reference - problematic
) { }
```

Do:
```java
public record Order(
    String id,
    String customerId  // ID reference - better
) { }
```

This makes persistence and equality simpler.

### Hint 5b: Look at Task Manager's categories for entity relationships
Task Manager shows a complete category system:
- `Category` entity with id, name, color
- `CategoryRepository` for CRUD operations
- Tasks reference categories via `categoryId`
- Controller has `showTasksByCategory()` for filtering
- View has `promptSelectCategory()` for user selection

This is a great template for any "items with categories" design.

## Designing Interfaces

### Hint 6: View interface = what users see and do
List every:
- Display method: `showX(data): void`
- Input method: `promptForX(): DataType`
- Feedback method: `showError/Success(message): void`

### Hint 7: Repository interface = what you query
Beyond basic CRUD, add methods like:
- `findByCategory(String categoryId): List<T>`
- `findRecent(int limit): List<T>`
- `searchByKeyword(String keyword): List<T>`

### Hint 8: Do you need a Strategy?
Ask yourself: "Is there behavior that could vary?"
- Scoring in a game? → ScoringStrategy
- Sorting options? → Comparator (built-in strategy!)
- Formatting output? → FormatterStrategy
- Calculation methods? → CalculationStrategy

If no variable behavior, that's okay - not every project needs Strategy.

## Architecture

### Hint 9: Copy the example structure
Both example projects use:
```
app/
├── model/           # Records (entities)
├── repository/      # Data access interfaces + implementations
├── view/            # UI interface + implementations (Console, JavaFX, Mock)
├── controller/      # Business logic
├── App.java         # Console entry point
└── AppGUI.java      # JavaFX entry point (optional)
```

Your project can follow this exactly.

### Hint 9b: Consider multiple view implementations
Both example projects have THREE view implementations:
- `ConsoleView` - text-based, runs in terminal
- `JavaFXView` - graphical UI with buttons and dialogs
- `MockView` - for testing, no real I/O

The same controller works with all three! This demonstrates the power of interfaces.

### Hint 9c: Seed sample data for demonstration
Both example projects seed sample data on startup:
```java
private static void seedSampleData(Repository repo) {
    repo.save(Entity.create("Example 1", ...));
    repo.save(Entity.create("Example 2", ...));
}
```

This makes your app immediately usable when someone runs it. Great for demos!

### Hint 10: Draw it before coding
ASCII art architecture diagrams help you think:
```
User → View → Controller → Repository → Storage
              ↓
            Model
```

If you can't draw it, you don't understand it yet.

## Common Pitfalls

### Pitfall 1: Too vague interfaces
Bad: `void showData(Object data)`
Good: `void showRecipeList(List<Recipe> recipes)`

Specific interfaces are easier to implement and test.

### Pitfall 2: Too much in MVP
Your MVP should be what you can build in **one week**, leaving one week for polish and fixing issues.

### Pitfall 3: Forgetting about testing
Every interface you design should have a corresponding mock or in-memory implementation for testing.

## Getting Unstuck

If you're stuck on a section:
1. Look at how the example projects handle it
2. Discuss with a classmate
3. Write a simpler version first, then expand
4. Ask in class or during office hours

Remember: The proposal doesn't have to be perfect. It will be refined in Weeks 11-13.
