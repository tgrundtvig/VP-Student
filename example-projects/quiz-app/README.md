# Quiz Application - Example Project

An interactive quiz application demonstrating MVC, Repository, and Strategy patterns.

## Purpose

This project demonstrates:
- **Interface-first design** - Question types through polymorphism
- **MVC pattern** - Clean separation of concerns
- **Repository pattern** - Abstracted data persistence
- **Strategy pattern** - Swappable scoring algorithms
- **Dependency injection** - Testable, flexible architecture

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                          QuizApp                             │
│                   (Composition Root)                         │
└─────────────────────────────────────────────────────────────┘
                              │
                              │ creates & injects
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      QuizController                          │
│                  (Coordinates everything)                    │
└─────────────────────────────────────────────────────────────┘
    │              │                    │                │
    │ uses         │ uses               │ uses           │ uses
    ▼              ▼                    ▼                ▼
┌─────────┐  ┌──────────────┐  ┌───────────────┐  ┌────────────────┐
│QuizView │  │QuizRepository│  │ResultRepository│  │ScoringStrategy │
│(iface)  │  │   (iface)    │  │    (iface)     │  │    (iface)     │
└─────────┘  └──────────────┘  └───────────────┘  └────────────────┘
```

## Key Patterns Demonstrated

### 1. Polymorphism with Question Interface

```java
public interface Question {
    String getText();
    boolean checkAnswer(String answer);
    int getPoints();
    // ...
}

// Different question types implement the same interface
public record MultipleChoiceQuestion(...) implements Question { }
public record TrueFalseQuestion(...) implements Question { }
```

**Benefit:** The controller doesn't know what type of question it's displaying - it just calls the interface methods.

### 2. Strategy Pattern for Scoring

```java
public interface ScoringStrategy {
    int calculateScore(Question question, String answer, boolean correct);
}

// Different scoring rules
public class StandardScoring implements ScoringStrategy { }
public class PenaltyScoring implements ScoringStrategy { }
```

**Benefit:** Scoring rules can be changed without modifying any other code.

### 3. Repository Pattern

```java
public interface QuizRepository extends Repository<Quiz, String> {
    List<Quiz> findByCategory(String category);
}

public interface ResultRepository extends Repository<QuizResult, String> {
    List<QuizResult> getTopScores(String quizId, int limit);
}
```

**Benefit:** Storage mechanism is completely abstracted.

## Package Structure

```
dk.viprogram.quizapp/
├── model/                    # Domain entities
│   ├── Question.java        # Question interface
│   ├── MultipleChoiceQuestion.java
│   ├── TrueFalseQuestion.java
│   ├── Quiz.java
│   └── QuizResult.java
│
├── repository/              # Data access
│   ├── Repository.java
│   ├── QuizRepository.java
│   ├── ResultRepository.java
│   ├── InMemoryQuizRepository.java
│   └── InMemoryResultRepository.java
│
├── scoring/                 # Scoring strategies
│   ├── ScoringStrategy.java
│   ├── StandardScoring.java
│   └── PenaltyScoring.java
│
├── view/                    # User interface
│   ├── QuizView.java        # View interface
│   ├── ConsoleQuizView.java # Console implementation
│   ├── JavaFXQuizView.java  # JavaFX implementation
│   └── MockQuizView.java    # Test mock
│
├── controller/              # Business logic
│   └── QuizController.java
│
├── QuizApp.java            # Console entry point
└── QuizAppGUI.java         # JavaFX entry point
```

## Design Decisions

### Why Question is an Interface (not abstract class)

```java
// Interface allows multiple inheritance if needed
public interface Question {
    boolean checkAnswer(String answer);
}

// Records can implement interfaces but not extend classes
public record TrueFalseQuestion(...) implements Question { }
```

**Rationale:**
- Records are ideal for immutable data
- Records can't extend classes but can implement interfaces
- Interface allows maximum flexibility

### Why Strategy for Scoring

```java
// Controller receives scoring strategy
public QuizController(
    QuizView view,
    QuizRepository quizRepository,
    ResultRepository resultRepository,
    ScoringStrategy scoringStrategy  // <- Injected strategy
) { }
```

**Rationale:**
- Scoring rules vary (exam vs practice mode)
- Easy to add new strategies (time bonus, partial credit)
- Tests can use simple scoring

### Why Separate Quiz and QuizResult

```java
public record Quiz(String id, String title, List<Question> questions) { }
public record QuizResult(String id, String quizId, int score, ...) { }
```

**Rationale:**
- One quiz can have many results (different players)
- Results are historical records, quizzes are templates
- Clean separation of concerns

## Running the Application

```bash
# Build
mvn clean compile

# Run tests
mvn test

# Run the console application
mvn exec:java -Dexec.mainClass="dk.viprogram.quizapp.QuizApp"

# Run the JavaFX GUI application
mvn javafx:run
```

The project includes both console and JavaFX implementations of `QuizView`. Both use the same controller - demonstrating that the interface abstraction allows swapping UIs without changing business logic.

## Extension Ideas

### Add New Question Types

```java
public record FillInBlankQuestion(
    String id,
    String textWithBlank,  // "The capital of France is ____"
    String answer,
    // ...
) implements Question {
    @Override
    public boolean checkAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer.trim());
    }
}
```

### Add Time Bonus Scoring

```java
public class TimeBonusScoring implements ScoringStrategy {
    private final long startTime;

    @Override
    public int calculateScore(Question question, String answer, boolean correct) {
        if (!correct) return 0;
        long elapsed = System.currentTimeMillis() - startTime;
        double bonus = Math.max(0, 1 - (elapsed / 30000.0)); // Bonus decreases over 30s
        return (int) (question.getPoints() * (1 + bonus * 0.5));
    }
}
```

### Add Quiz Categories

```java
public record QuizCategory(String id, String name, String description) { }

public interface CategoryRepository extends Repository<QuizCategory, String> { }
```

## Lessons for Your Project

1. **Use interfaces for behavior variations** - Questions, scoring, views
2. **Use records for data** - Immutable, clear semantics
3. **Inject strategies** - Flexible behavior selection
4. **Keep entities simple** - Records with helper methods
5. **Test against interfaces** - MockView, InMemoryRepository
