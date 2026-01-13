# Memory Card Game Exercise

## Overview

In this exercise, you'll complete a Memory Card Game that demonstrates the **MVC (Model-View-Controller)** pattern with **View as an interface**. The game already has most of the infrastructure built - you need to implement 4 TODO methods that make it work.

This exercise shows the power of interface-first design:
- The **same Controller** works with Console, JavaFX, or Mock views
- You can **test game logic** without launching a GUI
- Swapping between UI implementations requires **zero code changes** to the Controller

## Learning Objectives

By completing this exercise, you will:
- Understand how MVC separates concerns (data, logic, display)
- See how View-as-interface enables multiple UI implementations
- Practice implementing methods that follow an interface contract
- Experience how mock views enable fast, reliable testing

## The Game

Memory is a card-matching game where:
1. Cards are placed face-down in a grid
2. Players flip two cards at a time
3. If the cards match, they stay face-up
4. If they don't match, they flip back down
5. The goal is to find all pairs in as few moves as possible

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    MemoryGameController                      │
│  Connects Model and View, handles game flow                  │
└─────────────────────────────────────────────────────────────┘
           │                                │
           │ Uses                           │ Uses
           ▼                                ▼
┌─────────────────────┐          ┌─────────────────────────────┐
│  MemoryGameModel    │          │    MemoryGameView           │
│  (Interface)        │          │    (Interface)              │
├─────────────────────┤          ├─────────────────────────────┤
│ - newGame()         │          │ - displayGrid()             │
│ - flipCard()        │          │ - displayStats()            │
│ - checkMatch()      │          │ - displayMessage()          │
│ - getGrid()         │          │ - displayGameOver()         │
│ - isGameOver()      │          │ - setCardClickHandler()     │
└─────────────────────┘          └─────────────────────────────┘
           △                                △
           │                      ┌─────────┴─────────┐
           │                      │                   │
┌─────────────────────┐   ┌──────────────┐   ┌───────────────┐
│SimpleMemoryGameModel│   │ConsoleMemory │   │JavaFXMemory   │
│                     │   │GameView      │   │GameView       │
└─────────────────────┘   └──────────────┘   └───────────────┘
```

## Your Tasks

You need to implement **4 TODO methods** across different MVC components:

### 1. Model: `SimpleMemoryGameModel.checkMatch()` (lines 162-184)

**Purpose:** Check if the two flipped cards match and update game state.

**What to implement:**
- Return `NEED_MORE_CARDS` if less than 2 cards are flipped
- Compare the symbols of the two flipped cards
- If they match: mark both as matched, increment `matchesFound`
- If they don't match: flip both cards back down
- Always: increment `moves`, clear the flipped cards list
- Return `MATCH` or `NO_MATCH` accordingly

**Hints in the code show you which helper methods to use.**

### 2. Console View: `ConsoleMemoryGameView.displayGrid()` (lines 60-89)

**Purpose:** Print the card grid to the console with row/column labels.

**Expected output format:**
```
    0  1  2  3
  +--+--+--+--+
0 | ?| ?| ?| ?|
  +--+--+--+--+
1 | ?| 5| 5| ?|
  +--+--+--+--+
```

**What to implement:**
- Print column headers
- For each row: print separator, row number, and cards
- Use `card.display()` to get what to show for each card

### 3. JavaFX View: `JavaFXMemoryGameView.createCardButton()` (lines 211-238)

**Purpose:** Create a styled button for a single card in the grid.

**What to implement:**
- Create a Button with `card.display()` as text
- Set the style based on card state (use the provided style constants)
- Set up click handler to notify the controller
- Disable the button if the card is already matched or face-up

### 4. Controller: `MemoryGameController.handleCardClick()` (lines 97-121)

**Purpose:** Handle what happens after two cards are flipped.

**What to implement:**
- Set `waitingForFlipBack = true` to prevent more clicks
- Use `view.scheduleAction()` to delay the match check (lets player see the cards)
- After delay: call `model.checkMatch()`, update view, show result message
- Check if game is over and handle accordingly

## Running the Game

### Run Tests First
```bash
mvn test
```

The tests will fail until you implement the TODO methods. Use the test output to guide your implementation.

### Console Version
```bash
mvn compile exec:java -Dexec.mainClass="dk.viprogram.week08.memory.ConsoleMemoryApp"
```

### JavaFX Version
```bash
mvn javafx:run -Pmemory
```

Or from IntelliJ: Run the `MemoryLauncher` class.

## Key Files

| File | Role | Your Task |
|------|------|-----------|
| `Card.java` | Record representing a card | Read only |
| `Position.java` | Record for grid position | Read only |
| `MemoryGameModel.java` | Model interface | Read only |
| `MemoryGameView.java` | View interface | Read only |
| `SimpleMemoryGameModel.java` | Model implementation | **TODO: checkMatch()** |
| `ConsoleMemoryGameView.java` | Console UI | **TODO: displayGrid()** |
| `JavaFXMemoryGameView.java` | JavaFX UI | **TODO: createCardButton()** |
| `MemoryGameController.java` | Game logic coordinator | **TODO: handleCardClick()** |
| `MockMemoryGameView.java` | Test mock | Read only |
| `MemoryGameTest.java` | Unit tests | Run to verify |

## Testing Strategy

The tests use a `MockMemoryGameView` that records all method calls. This demonstrates the power of View-as-interface:

```java
// No GUI needed for testing!
MockMemoryGameView mockView = new MockMemoryGameView();
MemoryGameController controller = new MemoryGameController(model, mockView);

// Simulate user actions
mockView.simulateNewGame(2, 2);
mockView.simulateCardClick(new Position(0, 0));

// Verify what was displayed
assertTrue(mockView.getDisplayedGrids().size() > 0);
```

## Suggested Order

1. **Start with `checkMatch()`** - The model is the foundation
2. **Then `displayGrid()`** - You can test the console version
3. **Then `createCardButton()`** - Get the JavaFX version working
4. **Finally `handleCardClick()`** - Complete the game loop

## Common Issues

### Cards showing as "?" after implementation
Make sure you're calling `card.display()`, not `card.symbol()`. The `display()` method handles the logic of showing the card back vs the symbol.

### JavaFX buttons not responding
Check that you're setting the `onAction` handler and that you're checking `if (cardClickHandler != null)` before calling it.

### Tests failing with UnsupportedOperationException
You haven't implemented the TODO method yet - the placeholder throws this exception.

### Window resizing issues
The `displayGrid` method handles window sizing. Make sure you're not modifying window size elsewhere.

## What You'll Learn

After completing this exercise, you'll understand:

1. **Why View should be an interface** - The same game logic works with Console, JavaFX, and Mock views without any changes to the Controller.

2. **How MVC separates concerns** - Model knows nothing about display, View knows nothing about game rules, Controller coordinates both.

3. **Why this enables testing** - With a mock view, you can test all game logic without launching a GUI.

4. **The interface-first approach** - The interfaces (`MemoryGameModel`, `MemoryGameView`) were designed first. Implementations follow the contract.

## Need Help?

- Check `../hints.md` for solution code (try yourself first!)
- Review the existing implemented methods for patterns to follow
- Run the tests frequently to check your progress
- The TODO comments contain step-by-step guidance
