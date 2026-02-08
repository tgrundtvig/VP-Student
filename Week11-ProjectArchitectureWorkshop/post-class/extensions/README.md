# Week 11 Extensions: Going Deeper

## For Students Who Want More

These extensions are for students who have completed their architecture document and want
additional challenges to strengthen their design.

## Extension 1: Peer Review Another Student's Architecture

**Goal:** Improve your design skills by reviewing someone else's work.

**Process:**
1. Exchange architecture documents with a classmate
2. For each interface, ask: "Could I write a test against this?"
3. For each record, ask: "Is every field necessary? Is anything missing?"
4. Write 3 specific strengths and 3 specific improvement suggestions
5. Discuss your reviews together

**Why this helps:** Reviewing others' designs deepens your own understanding. You will notice
patterns and anti-patterns that are harder to see in your own work.

## Extension 2: Add Sequence Diagrams

**Goal:** Document the flow of key operations using text-based sequence diagrams.

**Steps:**
1. Pick 3 important user operations (e.g., add item, search, delete)
2. For each, draw a sequence diagram showing which layer calls which

**Example:**
```
Operation: Add New Recipe

User -> View: selects "Add Recipe"
View -> Controller: promptForNewRecipe() returns Recipe data
Controller -> Controller: validates Recipe data
Controller -> Repository: save(recipe)
Repository -> Controller: confirmation
Controller -> View: showSuccess("Recipe added")
View -> User: displays confirmation
```

3. If the sequence feels complicated, your design might need simplification.

**Why this helps:** Sequence diagrams expose awkward designs. If a method needs to pass through
many layers or if the Controller needs to make many calls for one operation, the design can
likely be improved.

## Extension 3: Consider Error Handling

**Goal:** Plan how your system handles errors gracefully.

**Questions to answer:**
1. What happens if the user enters invalid data?
   - Where is validation done? (Controller, not View)
   - How is the error communicated to the user?

2. What happens if a Repository operation fails?
   - How does the Controller know it failed?
   - What message does the user see?

3. What happens if the user tries to delete something that does not exist?
   - Return type: `Optional<Entity>` or throw exception?

**Deliverable:** Add error scenarios to your architecture document. For each interface method,
note what happens on failure.

## Extension 4: Design a Second Repository Implementation

**Goal:** Prove your Repository interface is flexible by designing a file-based implementation.

**Steps:**
1. Review your Repository interface methods
2. For each method, sketch how a `JsonFileRepository` would implement it:
   - Where does it read from? (JSON file)
   - Where does it write to? (Same JSON file)
   - How does it handle concurrent access? (For now, assume single-user)

3. Verify that your interface does not leak implementation details:
   - No file paths in the interface
   - No storage-specific types
   - No methods that only make sense for one implementation

**Why this helps:** If your interface supports both InMemory and JsonFile implementations
without changes, it is well-designed.

## Extension 5: Create a Package Structure Diagram

**Goal:** Plan the exact Java package structure for your project.

**Example:**
```
dk.viprogram.yourproject/
    ├── model/
    │   ├── Recipe.java          (record)
    │   ├── Ingredient.java      (record)
    │   ├── Category.java        (record)
    │   └── Priority.java        (enum)
    ├── repository/
    │   ├── RecipeRepository.java       (interface)
    │   ├── CategoryRepository.java     (interface)
    │   └── inmemory/
    │       ├── InMemoryRecipeRepository.java
    │       └── InMemoryCategoryRepository.java
    ├── view/
    │   ├── RecipeView.java             (interface)
    │   ├── console/
    │   │   └── ConsoleRecipeView.java
    │   └── mock/
    │       └── MockRecipeView.java
    ├── controller/
    │   └── RecipeController.java
    └── RecipeApp.java              (composition root)
```

**Why this helps:** Having the package structure ready makes project setup in Week 14 much faster.

---

## Submission

Extensions are optional and not graded. However, completing them will make your design
significantly stronger and your Week 12-14 work much easier.
