# Week 11 Pre-Class Reading: From Idea to Architecture

## Introduction

You have a project idea. Maybe it is a recipe manager, a workout tracker, a booking system, or
something entirely different. The challenge now is turning that idea into a well-structured
architecture that you can implement in two weeks.

This reading covers the systematic process of decomposing an idea into components, identifying
entities and behaviors, organizing them into layers, and creating an interface hierarchy that
makes implementation straightforward.

**Key insight:** The better your architecture, the easier your implementation will be. Time spent
designing is time saved coding.

## Step 1: Break Your Idea into Components

### Start with User Actions

The fastest way to decompose a project is to list what the user can do:

```
Recipe Manager:
1. Browse all recipes
2. Search recipes by ingredient
3. View recipe details
4. Add a new recipe
5. Edit an existing recipe
6. Delete a recipe
7. Scale a recipe for different servings
8. Categorize recipes
9. Mark recipes as favorites
```

Each action hints at the data and behavior your system needs.

### Group Actions by Theme

Actions naturally cluster into themes:

```
CRUD Operations:     Add, Edit, Delete, Browse, View
Search/Filter:       Search by ingredient, Categorize
Business Logic:      Scale recipe, Mark as favorite
```

These clusters often correspond to different parts of your architecture.

### Identify the Nouns (Entities)

The nouns in your user stories become your entities:

```
From the actions above:
- Recipe (the core entity)
- Ingredient (part of a recipe)
- Category (for organizing recipes)
```

### Identify the Verbs (Behaviors)

The verbs become methods on your interfaces:

```
Browse/View/Search  -> View interface methods (display, prompt)
Add/Edit/Delete     -> Repository interface methods (save, update, delete)
Scale               -> Strategy interface method (scale)
Categorize          -> Repository query method (findByCategory)
```

## Step 2: Classify as Records or Interfaces

This is the central skill of the course. For each component, ask: **is this data or behavior?**

### Records: Pure Data Carriers

Use records when the component:
- Holds data but does not perform actions
- Is immutable (once created, does not change)
- Needs equality based on content
- Represents an entity in your domain

```java
// Records carry data
public record Recipe(
    String id,
    String title,
    String description,
    int servings,
    List<String> ingredientIds,  // References by ID
    String categoryId,           // References by ID
    boolean favorite
) {
    // Factory method for creation with generated ID
    public static Recipe create(String title, String description, int servings,
                                List<String> ingredientIds, String categoryId) {
        return new Recipe(
            java.util.UUID.randomUUID().toString(),
            title, description, servings, ingredientIds, categoryId, false
        );
    }

    // Methods return NEW instances (immutability)
    public Recipe markAsFavorite() {
        return new Recipe(id, title, description, servings, ingredientIds, categoryId, true);
    }
}
```

### Interfaces: Behavior Contracts

Use interfaces when the component:
- Performs actions (display, save, calculate)
- Has multiple possible implementations
- Needs to be swappable (testing, different UIs, different storage)

```java
// Interfaces define behavior
public interface RecipeView {
    void showRecipeList(List<Recipe> recipes);
    void showRecipeDetail(Recipe recipe);
    Recipe promptForNewRecipe(List<Category> categories);
    void showSuccess(String message);
    void showError(String message);
}

public interface RecipeRepository {
    void save(Recipe recipe);
    Optional<Recipe> findById(String id);
    List<Recipe> findAll();
    List<Recipe> findByCategory(String categoryId);
    List<Recipe> findFavorites();
    void delete(String id);
}
```

### The Decision Flowchart

```
Is it data that gets passed around?
    YES -> Record
    NO  -> Does it perform actions?
        YES -> Could there be multiple implementations?
            YES -> Interface
            NO  -> Concrete class (but consider interface anyway)
        NO  -> Probably a record
```

## Step 3: Organize into Architecture Layers

A well-structured application has clear layers. Each layer has a specific responsibility and
communicates only with its neighbors.

### The Four Standard Layers

```
┌─────────────────────────────────────────────┐
│                   VIEW                       │
│   Handles user interaction (display/input)   │
│   Interface: XxxView                         │
│   Implementations: ConsoleView, JavaFXView,  │
│                    MockView                  │
└─────────────────────┬───────────────────────┘
                      │ calls / is called by
┌─────────────────────▼───────────────────────┐
│                CONTROLLER                    │
│   Coordinates flow between View and data     │
│   Contains business logic                    │
│   Uses interfaces, never implementations     │
└──────────┬──────────────────────┬───────────┘
           │                      │
┌──────────▼──────────┐  ┌───────▼────────────┐
│       MODEL          │  │   REPOSITORY       │
│  Records (entities)  │  │  Data access        │
│  Enums               │  │  Interface: XxxRepo │
│  Value objects        │  │  Impl: InMemory,   │
│                      │  │        FileRepo     │
└──────────────────────┘  └────────────────────┘
```

### Layer Rules

1. **View** knows only about the Controller (indirectly, through being called)
2. **Controller** knows about View interface, Repository interfaces, and Model
3. **Repository** knows only about Model
4. **Model** knows about nothing else --- it is pure data

### Why Layers Matter

- **Testability:** Replace View with MockView, Repository with InMemoryRepository
- **Flexibility:** Swap ConsoleView for JavaFXView without changing Controller
- **Clarity:** Each layer has one job --- easy to understand and maintain
- **Parallel work:** Different developers can work on different layers

## Step 4: Create an Interface Hierarchy Diagram

An interface hierarchy diagram shows all interfaces, their methods, and how they relate.

### Example: Recipe Manager

```
                     ┌─────────────────────────┐
                     │      RecipeApp           │
                     │   (composition root)     │
                     └────────────┬────────────┘
                                  │ creates
                     ┌────────────▼────────────┐
                     │    RecipeController      │
                     │  - run(): void           │
                     │  - addRecipe(): void     │
                     │  - listRecipes(): void   │
                     │  - searchByIngredient()  │
                     │  - scaleRecipe(): void   │
                     └──┬──────────────────┬───┘
                        │                  │
            ┌───────────▼────┐    ┌────────▼──────────────┐
            │  RecipeView    │    │  RecipeRepository      │
            │  (interface)   │    │  (interface)           │
            │                │    │                        │
            │ +showList()    │    │ +save(Recipe)          │
            │ +showDetail()  │    │ +findById(String)      │
            │ +promptNew()   │    │ +findAll()             │
            │ +showSuccess() │    │ +findByCategory(String)│
            │ +showError()   │    │ +delete(String)        │
            └───────┬────────┘    └──────────┬────────────┘
                    │                        │
        ┌───────────┤                 ┌──────┤
        │           │                 │      │
   Console     JavaFX            InMemory  FileRepo
   View        View              Repo
```

### How to Draw Your Own

1. Start with the composition root (main class) at the top
2. Add the Controller directly below
3. Place all interfaces the Controller uses below it
4. Add implementations below each interface
5. Include method signatures on each interface

## Step 5: Evaluate Your Scope

### The Scope Spectrum

```
TOO SMALL                    JUST RIGHT                    TOO LARGE
─────────────────────────────────────────────────────────────────────
Single entity CRUD           2-4 entities with             5+ complex entities
No business logic            relationships                 External APIs
< 1 day to implement         Interesting queries           Database required
                             Strategy or enum usage         > 1 month to implement
                             2 weeks to implement
```

### Scope Checklist

Answer these questions. If you cannot answer all of them, your scope might be too vague:

- [ ] Can I list every entity and its properties?
- [ ] Can I list every interface and its methods?
- [ ] Can I describe every user operation in one sentence?
- [ ] Can I build an MVP in one week (leaving one week for polish)?
- [ ] Does the project demonstrate at least MVC + Repository patterns?
- [ ] Is there at least one interesting feature beyond basic CRUD?

### Signs Your Scope is Too Large

- You have more than 4 entities with complex relationships
- You need external services (APIs, databases, email)
- You cannot describe the MVP in under 5 bullet points
- You keep adding "and also..." features
- A classmate says "that sounds like a lot"

### Signs Your Scope is Too Small

- You have only one entity with basic CRUD
- There are no interesting queries or business rules
- You could implement it in an afternoon
- It does not demonstrate the patterns from class

## Examples: Good vs Overly Ambitious

### Good Scope: Personal Library

```
Entities: Book, Author, ReadingList
Interfaces: BookView, BookRepository, ReadingListRepository
Features: Add/edit books, create reading lists, track reading progress,
          search by author/genre, mark as read
Patterns: MVC, Repository, maybe Strategy for sorting
```

### Overly Ambitious: Social Reading Platform

```
Entities: User, Book, Review, ReadingList, Friend, Activity, Notification,
          BookClub, Discussion, Comment, Badge
Features: User authentication, friend system, activity feed,
          book clubs with discussions, achievement badges,
          external book API integration
```

The second project would take months. The first is achievable in two weeks.

### Good Scope: Workout Tracker

```
Entities: Exercise, Workout, WorkoutEntry
Interfaces: WorkoutView, ExerciseRepository, WorkoutRepository
Features: Create exercises, log workouts, view history,
          track personal records, filter by date range
Patterns: MVC, Repository, maybe Strategy for personal record calculation
```

### Overly Ambitious: Fitness Platform

```
Entities: User, Exercise, Workout, MealPlan, Food, NutrientProfile,
          Goal, Achievement, TrainingPlan, BodyMeasurement
Features: Workout planning, meal tracking, calorie counting,
          progress photos, social sharing, AI recommendations
```

## Checklist for a Solid Project Architecture

Before you present your pitch, verify:

### Entities
- [ ] Each entity is a record with clear, typed properties
- [ ] Entities reference each other by ID (not object reference)
- [ ] Each entity has a factory method for creation
- [ ] Immutability: methods return new instances

### Interfaces
- [ ] View interface with display and input methods
- [ ] Repository interface with CRUD and domain queries
- [ ] Optional: Strategy interface for configurable behavior
- [ ] All methods have clear parameter types and return types

### Layers
- [ ] Clear separation: Model, View, Controller, Repository
- [ ] Controller depends only on interfaces
- [ ] View and Repository are independently swappable

### Testability
- [ ] Can test Controller with MockView + InMemoryRepository
- [ ] Tests need no real I/O (no files, no console, no GUI)
- [ ] Each interface can have a test double

### Scope
- [ ] 2-4 entities
- [ ] At least one interesting feature beyond CRUD
- [ ] Achievable in 2 weeks
- [ ] Demonstrates MVC + Repository patterns

## Preparing Your 3-Minute Pitch

Structure your pitch as follows:

| Segment | Time | Content |
|---------|------|---------|
| Problem | 30s | What problem does your app solve? |
| User | 30s | Who uses it? One example user story. |
| Entities | 45s | What records define your domain? |
| Interfaces | 45s | What contracts define behavior? |
| Patterns | 30s | Which patterns from class will you use? |

**Practice once out loud before class.** Three minutes goes fast.

## Summary

Turning an idea into architecture is a systematic process:

1. **List user actions** --- what can the user do?
2. **Identify entities** (records) and **behaviors** (interfaces)
3. **Organize into layers** --- View, Controller, Model, Repository
4. **Draw the interface hierarchy** --- interfaces, methods, implementations
5. **Check scope** --- achievable in 2 weeks, demonstrates patterns

The goal is not perfection. The goal is a clear enough design that you can start implementing with
confidence. Your design will evolve during Weeks 12-14 as you receive more feedback and think
more deeply about the details.

---

**Next step:** Complete the pre-class exercises and prepare your 3-minute pitch!
