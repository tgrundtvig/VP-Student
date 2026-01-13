# Week 10 Extensions: Going Deeper

## For Students Who Want More

These extensions are for students who have completed their project proposal and want additional challenges.

## Extension 1: Prototype Your View Interface

**Goal:** Create a concrete implementation of your View interface before Week 11.

**Steps:**
1. Create a `MockView` class implementing your View interface
2. Implement all methods with simple queue/list tracking
3. Write a test that uses the MockView

**Why this helps:** You'll discover if your interface is well-designed before you commit to it.

## Extension 2: Compare with Real Systems

**Goal:** Analyze a real-world application in your domain.

**Steps:**
1. Find an open-source project similar to yours (GitHub)
2. Analyze its architecture using the framework from class
3. Document: What patterns does it use? What could you borrow?

**Examples to analyze:**
- Recipe manager: Look at "Tandoor Recipes" on GitHub
- Task manager: Look at "Todo.txt" or "Tasks.org"
- Finance tracker: Look at "GnuCash" or "Money Manager Ex"

## Extension 3: Design a Second Implementation

**Goal:** Prove your interfaces are flexible by designing two implementations.

**For Repository:**
- Design `InMemoryRepository` (HashMap-based)
- Design `JsonFileRepository` (file-based)

**For View:**
- Design `ConsoleView` (text-based)
- Design `JavaFXView` (GUI-based, just method signatures)

**Why this helps:** If your interface can support multiple implementations, it's well-designed.

## Extension 4: Write Interface Tests

**Goal:** Write tests against your interfaces before any implementation exists.

**Steps:**
1. Create a test file with tests for your main interface
2. Tests should describe behavior, not implementation
3. Use interface types, not concrete classes

**Example:**
```java
@Test
void savedRecipeCanBeFoundById() {
    Recipe recipe = Recipe.create("Test Recipe", ...);
    repository.save(recipe);

    Optional<Recipe> found = repository.findById(recipe.getId());

    assertTrue(found.isPresent());
    assertEquals("Test Recipe", found.get().title());
}
```

**Why this helps:** Forces you to think about contracts before implementation.

## Extension 5: Add a Strategy Pattern

**Goal:** Identify and design a Strategy pattern for your project.

**Consider:**
- What behavior could vary at runtime?
- What would different strategies look like?
- How would the user select a strategy?

**Deliverable:**
- Strategy interface definition
- At least 2 strategy implementations (just signatures)
- Explanation of how/where strategies are used

## Extension 6: Peer Review

**Goal:** Review another student's proposal and provide feedback.

**Process:**
1. Exchange proposals with a classmate
2. Use the rubric to assess their proposal
3. Write 3 strengths and 3 areas for improvement
4. Discuss feedback together

**Why this helps:** Teaching others deepens your own understanding.

## Extension 7: Plan Your Demo

**Goal:** Script what you'll show at the exam.

**Create:**
1. A 5-minute demo script
2. List of features you'll demonstrate
3. Sample data you'll use
4. Talking points for each feature

**Why this helps:** Knowing what you'll demo helps focus your implementation.

---

## Submission

Extensions are optional and not graded. However, sharing your extension work in class can help others and demonstrates mastery.
