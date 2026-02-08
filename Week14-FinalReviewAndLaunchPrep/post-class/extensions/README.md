# Week 14 Extensions: Going Deeper

## For Students Who Want More

These extensions are for students who have completed their project setup and want to
get ahead on implementation or strengthen their development practices.

## Extension 1: CI/CD Concepts

**Goal:** Understand continuous integration and how it can help your project.

**What is CI/CD?**
Continuous Integration (CI) means automatically running tests every time you push code.
This catches problems early and ensures your code always works.

**Simple CI for your project:**
If you push your project to GitHub, you can add a GitHub Actions workflow:

```yaml
# .github/workflows/test.yml
name: Run Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
      - run: mvn test
```

**Why this helps:** Every time you push, GitHub runs your tests automatically.
If tests fail, you know immediately.

**Steps:**
1. Push your project to a GitHub repository
2. Create the `.github/workflows/test.yml` file
3. Push again and watch the test run in the Actions tab

## Extension 2: Code Review Practices

**Goal:** Learn how professional developers review each other's code.

**Code review checklist for your project:**
- [ ] Does each class have a single responsibility?
- [ ] Are all dependencies on interfaces, not implementations?
- [ ] Do method names clearly describe what they do?
- [ ] Is JavaDoc present and accurate?
- [ ] Are there any magic numbers or strings that should be constants?
- [ ] Are error cases handled?
- [ ] Do tests cover the important scenarios?

**Steps:**
1. After implementing your first feature, review your own code using this checklist
2. Ask a classmate to review your code using the same checklist
3. Apply the feedback

**Why this helps:** Self-review catches issues before they compound. Peer review teaches
you patterns you might not discover on your own.

## Extension 3: Documentation Strategy

**Goal:** Plan what documentation your project needs beyond JavaDoc.

**Documentation types to consider:**

### README.md for your project
```markdown
# Your Project Name

## Description
What the project does and who it is for.

## How to Run
mvn clean compile
mvn exec:java -Dexec.mainClass="dk.viprogram.yourproject.YourApp"

## How to Test
mvn test

## Architecture
Brief description of layers and key interfaces.

## Features
List of implemented features.
```

### Architecture decision records
For any non-obvious design decision, document:
- What decision was made
- Why it was made
- What alternatives were considered

**Why this helps:** When you come back to your code after a break (or during the exam),
documentation helps you remember why things are the way they are.

## Extension 4: Implement Phase 1 (Records)

**Goal:** Get a head start on implementation by completing Phase 1 tonight.

**Steps:**
1. Implement all your records with fields, factory methods, toString
2. Implement all enums
3. Run model tests --- they should pass
4. Commit: `git commit -m "Implement model records and enums"`

**This is the most impactful extension:** having Phase 1 done means you start Week 1 of
implementation already ahead of schedule.

## Extension 5: Implement Phase 2 (InMemoryRepository)

**Goal:** If you completed Extension 4, continue with repositories.

**Steps:**
1. Implement InMemoryRepository for your main entity
2. Implement InMemoryRepository for secondary entities
3. Run repository tests --- they should pass
4. Commit: `git commit -m "Implement InMemory repositories"`

**If both Extensions 4 and 5 are done:** You have completed the foundation.
The Controller and View are all that remain, and your tests guide the way.

## Extension 6: Create a Sample Data Seeder

**Goal:** Create the seed data method for your composition root.

**Steps:**
1. Create 3-5 realistic sample entities for each type
2. Write a `seedSampleData()` method that saves them to repositories
3. Make sure the sample data showcases different features (categories, favorites, etc.)

**Example:**
```java
private static void seedSampleData(RecipeRepository recipeRepo,
                                   CategoryRepository categoryRepo) {
    // Categories
    Category desserts = Category.create("Desserts", "#FF6B6B");
    Category mains = Category.create("Main Courses", "#4ECDC4");
    categoryRepo.save(desserts);
    categoryRepo.save(mains);

    // Recipes
    recipeRepo.save(Recipe.create("Chocolate Cake",
        "Rich chocolate cake with ganache", 8,
        List.of(), desserts.id()));
    recipeRepo.save(Recipe.create("Tomato Soup",
        "Classic tomato soup with basil", 4,
        List.of(), mains.id()));
    // ... more
}
```

**Why this helps:** Sample data makes your application immediately interesting when it
starts. Great for demos and testing.

---

## Submission

Extensions are optional. However, Extensions 4 and 5 (implementing records and repositories)
directly advance your project and are highly recommended. Starting the 2-week period with
Phase 1 and 2 already complete gives you a significant advantage.

---

## Final Words

You have designed a complete software system from scratch using interface-first, top-down
design. Regardless of how far you get with the implementation, the design skills you have
developed will serve you throughout your programming career.

**The design is the hard part. Implementation is where you enjoy seeing it come to life.**
